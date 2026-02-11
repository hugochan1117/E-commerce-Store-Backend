package com.fsse2510.lab_project_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TransactionStatusNotPrepare extends RuntimeException {
    public TransactionStatusNotPrepare(Integer tid) {
        super("The transaction with the following tid is either processing or had gone through: " + tid);
    }
}
