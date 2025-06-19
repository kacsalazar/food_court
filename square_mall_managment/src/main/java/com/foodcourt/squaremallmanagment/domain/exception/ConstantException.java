package com.foodcourt.squaremallmanagment.domain.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ConstantException {

    USER_NOT_ALLOWED("The user is not allowed", "404-04"),
    USER_NOT_AUTHORIZED("The user is not authorized", "404-05"),
    DISH_NOT_FOUND("Dish not found", "404-06"),
    USER_NOT_FOUND("User not found", "404-07"),
    RESTAURANT_NOT_FOUND("Restaurant not found", "404-08"),
    INVALID_ORDER("You already have an order in this restaurant", "404-09"),
    INVALID_USER("The user is not valid", "404-10"),
    INVALID_NAME("The name of the restaurant cannot be empty.", "404-11"),
    INVALID_PRICE_DISH("The price of the dish must be an integer greater than 0.", "404-12"),
    INVALID_NIT_RESTAURANT("The NIT must contain only numbers.", "404-13"),
    INVALID_PHONE_NUMBER("The phone number must have a maximum of 13 characters and can start with +", "404-14"),
    INVALID_RESTAURANT_NAME("The name of the restaurant cannot contain only numbers.", "404-15");

    private final String error_message;
    private final String code_error;

}
