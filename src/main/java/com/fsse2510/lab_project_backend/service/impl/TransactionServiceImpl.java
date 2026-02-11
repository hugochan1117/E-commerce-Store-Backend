package com.fsse2510.lab_project_backend.service.impl;

import com.fsse2510.lab_project_backend.data.Stripe.domainObject.response.StripeResponseData;
import com.fsse2510.lab_project_backend.data.cart_item.entity.CartItemEntity;
import com.fsse2510.lab_project_backend.data.product.entity.ProductEntity;
import com.fsse2510.lab_project_backend.data.transaction.domainObject.response.TransactionResponseData;
import com.fsse2510.lab_project_backend.data.transaction.entity.TransactionEntity;
import com.fsse2510.lab_project_backend.data.transaction_product.entity.TransactionProductEntity;
import com.fsse2510.lab_project_backend.data.user.domainObject.request.FirebaseUserData;
import com.fsse2510.lab_project_backend.data.user.entity.UserEntity;
import com.fsse2510.lab_project_backend.exception.TransactionNotByUser;
import com.fsse2510.lab_project_backend.exception.TransactionNotFound;
import com.fsse2510.lab_project_backend.exception.TransactionStatusNotPrepare;
import com.fsse2510.lab_project_backend.exception.TransactionStatusNotProcessing;
import com.fsse2510.lab_project_backend.mapper.TransactionDataMapper;
import com.fsse2510.lab_project_backend.repository.ProductRepository;
import com.fsse2510.lab_project_backend.repository.TransactionRepository;
import com.fsse2510.lab_project_backend.service.*;
import com.stripe.model.checkout.Session;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);
    private final UserService userService;
    private final CartItemService cartItemService;
    private final TransactionDataMapper transactionDataMapper;
    private final TransactionRepository transactionRepository;
    private final StripeService stripeService;
    private final ProductRepository productRepository;

    public TransactionServiceImpl(UserService userService, CartItemService cartItemService, TransactionDataMapper transactionDataMapper, TransactionRepository transactionRepository, StripeService stripeService,ProductRepository productRepository) {
        this.userService = userService;
        this.cartItemService = cartItemService;
        this.transactionDataMapper = transactionDataMapper;
        this.transactionRepository = transactionRepository;
        this.stripeService = stripeService;
        this.productRepository = productRepository;
    }

    public TransactionResponseData createTransaction(FirebaseUserData firebaseUserData) {
        UserEntity userEntity = userService.getUserEntityByEmail(firebaseUserData);
        try {
            List<CartItemEntity> cartItemEntities = cartItemService.getCartItemListByUser(userEntity);
            TransactionEntity transactionEntity = new TransactionEntity();
            BigDecimal total = new BigDecimal(0);
            for (CartItemEntity cartItemEntity : cartItemEntities) {
                TransactionProductEntity transactionProductEntity = new TransactionProductEntity();
                transactionProductEntity.setTransaction(transactionEntity);
                transactionProductEntity.setProduct(cartItemEntity.getProduct());
                transactionProductEntity.setQuantity(cartItemEntity.getQuantity());
                transactionProductEntity.setSubtotal(cartItemEntity.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItemEntity.getQuantity())));
                transactionEntity.getTransactionProductEntityList().add(transactionProductEntity);
                total = total.add(transactionProductEntity.getSubtotal());
            }
            transactionEntity.setUser(userEntity);
            transactionEntity.setStatus("PREPARE");
            transactionEntity.setTotal(total);
            transactionRepository.save(transactionEntity);
            return transactionDataMapper.toTransactionResponseData(transactionEntity);

        } catch (Exception e) {
            logger.warn("Transaction creation failed: {}", e.getMessage());
            throw e;
        }

    }

    public TransactionResponseData getTransactionResponseDataByTid(FirebaseUserData firebaseUserData, Integer tid) {
        UserEntity userEntity = userService.getUserEntityByEmail(firebaseUserData);
        try {
            TransactionEntity transactionEntity = getTransactionEntityByTid(tid);
            if (transactionEntity.getUser().getUid().equals(userEntity.getUid())) {
                return transactionDataMapper.toTransactionResponseData(transactionEntity);
            } else {
                throw new TransactionNotByUser(tid);
            }
        } catch (Exception e) {
            logger.warn("Get transaction failed: {}", e.getMessage());
            throw e;
        }
    }

    public StripeResponseData updateTransactionStatusToProcessing(FirebaseUserData firebaseUserData, Integer tid) throws Exception {
        UserEntity userEntity = userService.getUserEntityByEmail(firebaseUserData);
        try {
            TransactionEntity transactionEntity = getTransactionEntityByTid(tid);

            if (!transactionEntity.getUser().getUid().equals(userEntity.getUid())) {
                throw new TransactionNotByUser(tid);
            }

            if (!"PREPARE".equals(transactionEntity.getStatus())) {
                throw new TransactionStatusNotPrepare(tid);
            }

            // update status
            transactionEntity.setStatus("PROCESSING");


            // create Stripe session
            Session session = stripeService.createCheckOutSession(transactionEntity);
            transactionEntity.setSessionId(session.getId());
            transactionRepository.save(transactionEntity);
            StripeResponseData stripeResponseData = new StripeResponseData();
            stripeResponseData.setUrl(session.getUrl());
            return stripeResponseData;

        } catch (Exception e) {
            logger.warn("Transaction status update failed: {}", e.getMessage());
            throw e;
        }
    }

    @Transactional
    public TransactionResponseData updateTransactionStatusToSuccess(FirebaseUserData firebaseUserData, Integer tid) throws Exception {
        UserEntity userEntity = userService.getUserEntityByEmail(firebaseUserData);
        try {
            TransactionEntity transactionEntity = getTransactionEntityByTid(tid);

            if (!transactionEntity.getUser().getUid().equals(userEntity.getUid())) {
                throw new TransactionNotByUser(tid);
            }

            if (!"PROCESSING".equals(transactionEntity.getStatus())) {
                throw new TransactionStatusNotProcessing(tid);
            }

            // Retrieve Stripe sessionId from DB
            String sessionId = transactionEntity.getSessionId();
            if (sessionId == null) {
                throw new IllegalStateException("No Stripe sessionId found for transaction " + tid);
            }

            // Verify with Stripe
            Session session = Session.retrieve(sessionId);
            if (!"paid".equals(session.getPaymentStatus())) {
                throw new IllegalStateException("Stripe session not paid for transaction " + tid);
            }

            // Update status to SUCCESS
            transactionEntity.setStatus("SUCCESS");
            transactionRepository.save(transactionEntity);

            for (CartItemEntity item : cartItemService.getCartItemListByUser(userEntity)) {
                ProductEntity product = item.getProduct();
                int newStock = product.getStock() - item.getQuantity();
                if (newStock < 0) {
                    throw new IllegalStateException("Insufficient stock for product " + product.getPid());
                }
                productRepository.updateStock(product.getPid(), newStock);
            }

            // Clear cart items
            cartItemService.deleteAllItems(userEntity);


            return transactionDataMapper.toTransactionResponseData(transactionEntity);

        } catch (Exception e) {
            logger.warn("Transaction status update failed: {}", e.getMessage());
            throw e;
        }
    }

    public List<TransactionResponseData> getOrderHistory(FirebaseUserData firebaseUserData){
        UserEntity userEntity = userService.getUserEntityByEmail(firebaseUserData);
        return transactionDataMapper.toTransactionResponseDataList(transactionRepository.findAllByUserAndStatus(userEntity,"SUCCESS"));
    }


    public TransactionEntity getTransactionEntityByTid(Integer tid) {
        Optional<TransactionEntity> transactionEntityOptional = transactionRepository.findByTid(tid);
        if (transactionEntityOptional.isEmpty()) {
            throw new TransactionNotFound(tid);
        } else {
            return transactionEntityOptional.get();
        }
    }

}

