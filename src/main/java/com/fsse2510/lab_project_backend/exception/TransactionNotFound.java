package com.fsse2510.lab_project_backend.exception;

public class TransactionNotFound extends RuntimeException {
    public TransactionNotFound(Integer tid) {
        super("Transaction with this Tid was not found: "+ tid);
    }
}
