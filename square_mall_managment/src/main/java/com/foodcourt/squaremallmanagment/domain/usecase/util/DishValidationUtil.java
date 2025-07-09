package com.foodcourt.squaremallmanagment.domain.usecase.util;

import com.foodcourt.squaremallmanagment.domain.exception.InvalidNameException;
import com.foodcourt.squaremallmanagment.domain.exception.InvalidPriceDishException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DishValidationUtil {

    public static void isValidDish(String name ,  Double price) {

        if (name == null || name.isBlank()) {
            //The name of the dish cannot be empty
            throw new InvalidNameException();
        }

        if (price == null || price <= 0) {
            throw new InvalidPriceDishException();
                    //IllegalArgumentException("The price of the dish must be an integer greater than 0.");
        }
    }
}
