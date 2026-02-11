package com.fsse2510.lab_project_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException(Integer pid) {
        super("The product with the following Pid has insufficient stock: " + pid);
    }
}
