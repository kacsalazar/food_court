package com.foodcourt.squaremallmanagment.infrastructure.exceptionhandler;

public enum ExceptionResponse {
    NO_DATA_FOUND("No data found for the requested petition"),
    INVALID_USER("Invalid user credentials provided"),
    DISH_NOT_FOUND("Dish not found"),
    RESTAURANT_NOT_FOUND("Restaurant not found"),
    USER_NOT_FOUND("User not found");

    private final String message;

    ExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}