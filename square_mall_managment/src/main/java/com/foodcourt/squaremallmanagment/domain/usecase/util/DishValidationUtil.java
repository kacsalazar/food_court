package com.foodcourt.squaremallmanagment.domain.usecase.util;

import com.foodcourt.squaremallmanagment.domain.model.DishModel;
import lombok.experimental.UtilityClass;
import org.springframework.stereotype.Component;

@UtilityClass
public class DishValidationUtil {

    public static void isValidDish(DishModel dishModel) {

        if (dishModel.getName() == null || dishModel.getName().isBlank()) {
            throw new IllegalArgumentException("The name of the dish cannot be empty");
        }

        if (dishModel.getPrice() == null || dishModel.getPrice() <= 0) {
            throw new IllegalArgumentException("The price of the dish must be an integer greater than 0.");
        }
    }
}
