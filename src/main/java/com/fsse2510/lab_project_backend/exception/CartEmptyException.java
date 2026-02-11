package com.fsse2510.lab_project_backend.exception;

public class CartEmptyException extends RuntimeException {
    public CartEmptyException() {
        super("Cart Empty!");
    }
}
