package com.foodcourt.squaremallmanagment.domain.usecase.util;

import com.foodcourt.squaremallmanagment.domain.exception.ConstantException;
import com.foodcourt.squaremallmanagment.domain.exception.DomainException;
import com.foodcourt.squaremallmanagment.domain.model.DishModel;
import lombok.experimental.UtilityClass;
import org.springframework.stereotype.Component;

@UtilityClass
public class DishValidationUtil {

    public static void isValidDish(DishModel dishModel) {

        if (dishModel.getDishInfo().getName() == null ||dishModel.getDishInfo().getName().isBlank()) {
            throw new IllegalArgumentException("The name of the dish cannot be empty");
        }

        if (dishModel.getDishInfo().getPrice() == null || dishModel.getDishInfo().getPrice() <= 0) {
            throw new IllegalArgumentException("The price of the dish must be an integer greater than 0.");
        }
    }
}
