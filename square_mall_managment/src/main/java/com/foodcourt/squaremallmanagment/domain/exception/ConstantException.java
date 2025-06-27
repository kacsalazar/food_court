package com.foodcourt.squaremallmanagment.domain.exception;

public enum ConstantException {

    INVALID_USER("Invalid user credentials provided"),
    DISH_NOT_FOUND("Dish not found"),
    RESTAURANT_NOT_FOUND("Restaurant not found"),
    USER_NOT_FOUND("User not found"),
    ORDER_NOT_FOUND("Order not found"),
    INVALID_ORDER("You have a order in progress, please finish it before creating a new one"),
    INVALID_STATE_TRANSITION("Invalid state transition for the order"),
    INVALID_PIN_SECURITY("Invalid security pin provided"),;

    private final String message;

    ConstantException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

}
