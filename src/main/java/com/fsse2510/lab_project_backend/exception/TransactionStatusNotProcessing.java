package com.fsse2510.lab_project_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TransactionStatusNotProcessing extends RuntimeException {
    public TransactionStatusNotProcessing(Integer tid) {
        super("Transaction with the following tid had succeeded or was not processed: " + tid);
    }
}
