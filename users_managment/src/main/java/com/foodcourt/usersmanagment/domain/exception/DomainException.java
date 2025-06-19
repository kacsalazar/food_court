package com.foodcourt.usersmanagment.domain.exception;

public class DomainException extends RuntimeException {

    private ConstantException constant;

    public DomainException(ConstantException exception) {
        super(exception.getError_message());
        constant = exception;
    }
}
