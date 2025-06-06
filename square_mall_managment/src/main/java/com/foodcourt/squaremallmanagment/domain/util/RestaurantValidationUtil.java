package com.foodcourt.squaremallmanagment.domain.util;

import com.foodcourt.squaremallmanagment.domain.model.RestaurantModel;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class RestaurantValidationUtil {

    public void isValidRestaurant(RestaurantModel restaurantModel) {

        if (restaurantModel.getNit() == null || !restaurantModel.getNit().matches("\\d+")) {
            throw new IllegalArgumentException("El NIT debe contener únicamente números.");
        }

        if (restaurantModel.getPhoneNumber() == null ||
                !Pattern.matches("^\\+?\\d{1,13}$", restaurantModel.getPhoneNumber())) {
            throw new IllegalArgumentException("El teléfono debe tener máximo 13 caracteres y puede comenzar con '+'.");
        }

        if (restaurantModel.getName() == null || restaurantModel.getName().isBlank()) {
            throw new IllegalArgumentException("El nombre del restaurante no puede estar vacío.");
        }

        if (restaurantModel.getName().matches("\\d+")) {
            throw new IllegalArgumentException("El nombre del restaurante no puede contener solo números.");
        }
    }
}
