package com.fsse2510.lab_project_backend.service.impl;

import com.fsse2510.lab_project_backend.data.cart_item.domainObject.response.CartItemResponseData;
import com.fsse2510.lab_project_backend.data.cart_item.entity.CartItemEntity;
import com.fsse2510.lab_project_backend.data.product.entity.ProductEntity;
import com.fsse2510.lab_project_backend.data.user.domainObject.request.FirebaseUserData;
import com.fsse2510.lab_project_backend.data.user.entity.UserEntity;
import com.fsse2510.lab_project_backend.exception.CartEmptyException;
import com.fsse2510.lab_project_backend.exception.CartItemNotFoundException;
import com.fsse2510.lab_project_backend.exception.InsufficientStockException;
import com.fsse2510.lab_project_backend.mapper.CartItemDataMapper;
import com.fsse2510.lab_project_backend.repository.CartItemRepository;
import com.fsse2510.lab_project_backend.service.CartItemService;
import com.fsse2510.lab_project_backend.service.ProductService;
import com.fsse2510.lab_project_backend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {
    private final UserService userService;
    private final ProductService productService;
    private final Logger logger = LoggerFactory.getLogger(CartItemServiceImpl.class);
    private final CartItemRepository cartItemRepository;
    private final CartItemDataMapper cartItemDataMapper;

    public CartItemServiceImpl(UserService userService, ProductService productService, CartItemRepository cartItemRepository, CartItemDataMapper cartItemDataMapper) {
        this.userService = userService;
        this.productService = productService;
        this.cartItemRepository = cartItemRepository;
        this.cartItemDataMapper = cartItemDataMapper;
    }

    // change to account for not enough stock or adding an item that is already in the basket.
    public void putItemInCart(FirebaseUserData firebaseUserData, Integer pid, Integer quantity) {
        try {
            UserEntity userEntity = userService.getUserEntityByEmail(firebaseUserData);
            ProductEntity productEntity = productService.getProductEntityByPid(pid);
            Optional<CartItemEntity> cartItemEntityOptional = cartItemRepository.findByUserAndProduct(userEntity, productEntity);
            if (cartItemEntityOptional.isEmpty()) {
                if (quantity > productEntity.getStock()) {
                    throw new InsufficientStockException(pid);
                }
                CartItemEntity cartItemEntity = new CartItemEntity();
                cartItemEntity.setUser(userEntity);
                cartItemEntity.setProduct(productEntity);
                cartItemEntity.setQuantity(quantity);
                cartItemRepository.save(cartItemEntity);
            } else {
                CartItemEntity cartItemEntity = cartItemEntityOptional.get();
                if ((cartItemEntity.getQuantity() + quantity) > productEntity.getStock()) {
                    throw new InsufficientStockException(pid);
                }
                cartItemEntity.setQuantity(cartItemEntity.getQuantity()+quantity);
                cartItemRepository.save(cartItemEntity);
            }
        } catch (Exception e) {
            logger.warn("Put item in cart failed: {}", e.getMessage());
            throw e;
        }


    }

    public void updateCartQuantity(FirebaseUserData firebaseUserData, Integer pid, Integer quantity) {
        try {
            UserEntity userEntity = userService.getUserEntityByEmail(firebaseUserData);
            ProductEntity productEntity = productService.getProductEntityByPid(pid);
            if (quantity > productEntity.getStock()){
                throw new InsufficientStockException(pid);
            }
            CartItemEntity cartItemEntity = getCartItemEntityByUserAndProduct(userEntity, productEntity);
            cartItemEntity.setQuantity(quantity);
            cartItemRepository.save(cartItemEntity);

        } catch (Exception e) {
            logger.warn("Update quantity failed: {}", e.getMessage());
            throw e;
        }
    }

    public void deleteCartProduct(FirebaseUserData firebaseUserData, Integer pid) {
        try {
            UserEntity userEntity = userService.getUserEntityByEmail(firebaseUserData);
            ProductEntity productEntity = productService.getProductEntityByPid(pid);
            CartItemEntity cartItemEntity = getCartItemEntityByUserAndProduct(userEntity, productEntity);
            cartItemRepository.delete(cartItemEntity);

        } catch (Exception e) {
            logger.warn("Delete product in cart failed: {}", e.getMessage());
            throw e;
        }
    }


    public List<CartItemResponseData> getUserCart(FirebaseUserData firebaseUserData) {
        UserEntity user = userService.getUserEntityByEmail(firebaseUserData);
        List<CartItemEntity> cartItems = cartItemRepository.findAllByUser(user);
        return cartItemDataMapper.toCartItemResponseDataList(cartItems);

    }

    public CartItemEntity getCartItemEntityByUserAndProduct(UserEntity user, ProductEntity product) {
        Optional<CartItemEntity> cartItemEntityOptional = cartItemRepository.findByUserAndProduct(user, product);
        if (cartItemEntityOptional.isEmpty()) {
            throw new CartItemNotFoundException(product.getPid());
        } else {
            return cartItemEntityOptional.get();
        }

    }

    public List<CartItemEntity> getCartItemListByUser(UserEntity user) {
        List<CartItemEntity> cartItemEntityList = cartItemRepository.findAllByUser(user);
        if (cartItemEntityList.isEmpty()) {
            throw new CartEmptyException();
        } else {
            return cartItemEntityList;
        }
    }

    public void deleteAllItems(UserEntity user){
        cartItemRepository.deleteAllByUser(user);
    }
}
