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
            throw new DomainException(ConstantException.INVALID_NAME);
        }

        if (dishModel.getDishInfo().getPrice() == null || dishModel.getDishInfo().getPrice() <= 0) {
            throw new DomainException(ConstantException.INVALID_PRICE_DISH);
        }
    }
}
