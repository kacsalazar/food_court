package com.foodcourt.squaremallmanagment.domain.util;

import com.foodcourt.squaremallmanagment.domain.model.RestaurantModel;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class RestaurantValidationUtil {

    public void isValidRestaurant(RestaurantModel restaurantModel) {

        if (restaurantModel.getNit() == null || !restaurantModel.getNit().matches("\\d+")) {
            throw new IllegalArgumentException("The NIT must contain only numbers.");
        }

        if (restaurantModel.getPhoneNumber() == null ||
                !Pattern.matches("^\\+?\\d{1,13}$", restaurantModel.getPhoneNumber())) {
            throw new IllegalArgumentException("The phone number must be a maximum of 13 characters and may begin with '+'.");
        }

        if (restaurantModel.getName() == null || restaurantModel.getName().isBlank()) {
            throw new IllegalArgumentException("The name of the restaurant cannot be empty.");
        }

        if (restaurantModel.getName().matches("\\d+")) {
            throw new IllegalArgumentException("The name of the restaurant cannot contain only numbers.");
        }
    }
}
