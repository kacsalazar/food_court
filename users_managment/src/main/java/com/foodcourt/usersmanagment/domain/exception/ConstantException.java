package com.foodcourt.usersmanagment.domain.exception;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ConstantException {

    INVALID_EMAIL("The email is invalid", "404-01"),
    INVALID_PHONE_NUMBER("The phone number must have a maximum of 13 characters and can start with +", "404-01"),
    INVALID_DNI("The document must contain only numbers", "404-01"),
    INVALID_BIRTHDAY_DATE("The user must be of legal age", "404-01"),
    USER_NOT_FOUND("User not found", "404-02"),
    INVALID_USER("The user is not valid", "404-03"),
    ROLE_NOT_FOUND("Role not found", "404-04"),
    INVALID_PASSWORD("Invalid password", "404-05");

    private final String error_message;
    private final String code_error;
}
