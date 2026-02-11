package com.fsse2510.lab_project_backend.service;
import com.stripe.model.checkout.Session;
import com.fsse2510.lab_project_backend.data.transaction.entity.TransactionEntity;

public interface StripeService {
    Session createCheckOutSession(TransactionEntity transactionEntity) throws Exception;
}
