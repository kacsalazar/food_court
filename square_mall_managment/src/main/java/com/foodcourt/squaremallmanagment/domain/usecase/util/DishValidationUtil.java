package com.foodcourt.squaremallmanagment.domain.usecase.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class DishValidationUtil {

    public static void isValidDish(String name ,  Double price) {

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("The name of the dish cannot be empty");
        }

        if (price == null || price <= 0) {
            throw new IllegalArgumentException("The price of the dish must be an integer greater than 0.");
        }
    }
}
