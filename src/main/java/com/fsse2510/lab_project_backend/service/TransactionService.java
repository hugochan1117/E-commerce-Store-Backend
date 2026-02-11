package com.fsse2510.lab_project_backend.service;

import com.fsse2510.lab_project_backend.data.Stripe.domainObject.response.StripeResponseData;
import com.fsse2510.lab_project_backend.data.transaction.domainObject.response.TransactionResponseData;
import com.fsse2510.lab_project_backend.data.user.domainObject.request.FirebaseUserData;

import java.util.List;

public interface TransactionService {
    TransactionResponseData createTransaction(FirebaseUserData firebaseUserData);

    TransactionResponseData getTransactionResponseDataByTid(FirebaseUserData firebaseUserData, Integer tid);

    StripeResponseData updateTransactionStatusToProcessing(FirebaseUserData firebaseUserData, Integer tid) throws Exception;

    TransactionResponseData updateTransactionStatusToSuccess(FirebaseUserData firebaseUserData, Integer tid) throws Exception;

    List<TransactionResponseData> getOrderHistory(FirebaseUserData firebaseUserData);
}
