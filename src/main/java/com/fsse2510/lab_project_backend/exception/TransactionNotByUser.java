package com.fsse2510.lab_project_backend.exception;

public class TransactionNotByUser extends RuntimeException {
    public TransactionNotByUser(Integer tid) {
        super("The transaction with the following Tid does not belong to the current user: " + tid);
    }
}
