package com.foodcourt.squaremallmanagment.domain.usecase.util;

import com.foodcourt.squaremallmanagment.domain.exception.InvalidNameException;
import com.foodcourt.squaremallmanagment.domain.exception.InvalidNameRestaurantException;
import com.foodcourt.squaremallmanagment.domain.exception.InvalidNitRestaurantException;
import com.foodcourt.squaremallmanagment.domain.exception.InvalidPhoneNumberRestaurantException;
import com.foodcourt.squaremallmanagment.domain.model.restaurant.RestaurantModel;
import lombok.experimental.UtilityClass;

import java.util.regex.Pattern;

@UtilityClass
public class RestaurantValidationUtil {

    public static void isValidRestaurant(RestaurantModel restaurantModel) {

        if (restaurantModel.getNit() == null || !restaurantModel.getNit().matches("\\d+")) {
            throw new InvalidNitRestaurantException();
        }

        if (restaurantModel.getPhoneNumber() == null ||
                !Pattern.matches("^\\+?\\d{1,13}$", restaurantModel.getPhoneNumber())) {
            throw new InvalidPhoneNumberRestaurantException();
        }

        if (restaurantModel.getName() == null || restaurantModel.getName().isBlank()) {
            throw new InvalidNameException();
        }

        if (restaurantModel.getName().matches("\\d+")) {
            throw new InvalidNameRestaurantException();
        }
    }
}
