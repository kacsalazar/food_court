package com.foodcourt.squaremallmanagment.domain.api;

import com.foodcourt.squaremallmanagment.domain.model.DishModel;

public interface IDishServicePort {

    void saveDish(DishModel dishModel);
}
