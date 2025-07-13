package com.foodcourt.traceabilitymanagement.domain.exception;

public enum ConstantException {

    NOT_PERMISSION("You do not have permission to perform this action"),
    RESTAURANT_NOT_FOUND("Restaurant not found"),;

    private final String message;

    ConstantException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
