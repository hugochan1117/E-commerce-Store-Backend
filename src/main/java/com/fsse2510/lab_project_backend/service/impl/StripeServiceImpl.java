package com.fsse2510.lab_project_backend.service.impl;

import com.fsse2510.lab_project_backend.config.EnvConfig;
import com.stripe.Stripe;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import com.fsse2510.lab_project_backend.data.transaction.entity.TransactionEntity;
import com.fsse2510.lab_project_backend.data.transaction_product.entity.TransactionProductEntity;
import com.fsse2510.lab_project_backend.service.StripeService;
import org.springframework.stereotype.Service;

@Service
public class StripeServiceImpl implements StripeService {


    @Override
    public Session createCheckOutSession(TransactionEntity transactionEntity) throws Exception {
        // Set your secret key
        Stripe.apiKey =  System.getenv("STRIPE_SECRET_KEY");

        String YOUR_DOMAIN = EnvConfig.PROD_BASE_URL;

        SessionCreateParams.Builder paramsBuilder = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl(YOUR_DOMAIN + "/OrderConfirmationPage/?tid=" + transactionEntity.getTid())
                .setCancelUrl(YOUR_DOMAIN + "/ShoppingCart/");

        // Add each product from the transaction dynamically
        for (TransactionProductEntity productEntity : transactionEntity.getTransactionProductEntityList()) {
            paramsBuilder.addLineItem(
                    SessionCreateParams.LineItem.builder()
                            .setQuantity(productEntity.getQuantity().longValue())
                            .setPriceData(
                                    SessionCreateParams.LineItem.PriceData.builder()
                                            .setCurrency("usd")
                                            .setUnitAmount(
                                                    productEntity.getProduct().getPrice()
                                                            .multiply(new java.math.BigDecimal(100))
                                                            .longValue()
                                            )
                                            .setProductData(
                                                    SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                            .setName(productEntity.getProduct().getName())
                                                            // add image URL(s) here
                                                            .addImage(productEntity.getProduct()
                                                                    .getImages()
                                                                    .getFirst()
                                                                    .getImageUrl()
                                                            )
                                                            .build()
                                            )
                                            .build()
                            )
                            .build()
            );
        }


        SessionCreateParams params = paramsBuilder.build();


        return Session.create(params);
    }
}
