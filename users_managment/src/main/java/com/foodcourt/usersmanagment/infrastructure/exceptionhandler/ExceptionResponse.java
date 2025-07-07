package com.foodcourt.usersmanagment.infrastructure.exceptionhandler;

public enum ExceptionResponse {
    NO_DATA_FOUND("No data found for the requested petition"),
    USER_NOT_FOUND("User not found"),
    ROL_NOT_FOUND("Rol not found"),
    INVALID_PASSWORD("Invalid password"),
    USER_NOT_AUTHORIZED("User not authorized"),
    INVALID_EMAIL("The email is invalid"),
    INVALID_PHONE_NUMBER("The phone number must have a maximum of 13 characters and can start with +"),
    INVALID_DNI("The document must contain only numbers"),
    INVALID_BIRTHDAY_DATE("The user must be of legal age"),;

    private final String message;

    ExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}