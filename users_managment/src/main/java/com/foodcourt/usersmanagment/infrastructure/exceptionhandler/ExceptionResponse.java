package com.foodcourt.usersmanagment.infrastructure.exceptionhandler;

public enum ExceptionResponse {
    NO_DATA_FOUND("No data found for the requested petition"),
    USER_NOT_FOUND("User not found"),
    ROL_NOT_FOUND("Rol not found"),
    INVALID_PASSWORD("Invalid password"),;

    private final String message;

    ExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}