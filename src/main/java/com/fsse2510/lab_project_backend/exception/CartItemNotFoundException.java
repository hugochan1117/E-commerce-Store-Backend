package com.fsse2510.lab_project_backend.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CartItemNotFoundException extends RuntimeException {
    public CartItemNotFoundException(Integer pid) {
        super("Product of the following pid was not found in user cart: " + pid);
    }
}
