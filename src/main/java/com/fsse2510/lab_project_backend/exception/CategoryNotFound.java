package com.fsse2510.lab_project_backend.exception;

public class CategoryNotFound extends RuntimeException {
    public CategoryNotFound(String category) {
        super("This category was not found: " + category);
    }
}
