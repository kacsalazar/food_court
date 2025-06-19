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
            throw new DomainException(ConstantException.INVALID_NIT_RESTAURANT);
        }

        if (restaurantModel.getPhoneNumber() == null ||
                !Pattern.matches("^\\+?\\d{1,13}$", restaurantModel.getPhoneNumber())) {
            throw new DomainException(ConstantException.INVALID_PHONE_NUMBER);
        }

        if (restaurantModel.getName() == null || restaurantModel.getName().isBlank()) {
            throw new DomainException(ConstantException.INVALID_NAME);
        }

        if (restaurantModel.getName().matches("\\d+")) {
            throw new DomainException(ConstantException.INVALID_RESTAURANT_NAME);
        }
    }
}
