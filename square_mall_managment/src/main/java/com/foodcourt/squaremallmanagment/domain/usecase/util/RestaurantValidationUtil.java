package com.foodcourt.squaremallmanagment.domain.usecase.util;

import com.foodcourt.squaremallmanagment.domain.exception.ConstantException;
import com.foodcourt.squaremallmanagment.domain.exception.DomainException;
import com.foodcourt.squaremallmanagment.domain.model.RestaurantModel;
import lombok.experimental.UtilityClass;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@UtilityClass
public class RestaurantValidationUtil {

    public static void isValidRestaurant(RestaurantModel restaurantModel) {

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
