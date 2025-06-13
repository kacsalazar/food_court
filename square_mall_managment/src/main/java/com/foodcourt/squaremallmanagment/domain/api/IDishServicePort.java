package com.foodcourt.squaremallmanagment.domain.api;

import com.foodcourt.squaremallmanagment.domain.model.DishModel;
import com.foodcourt.squaremallmanagment.domain.model.DishUpdateModel;

public interface IDishServicePort {

    void saveDish(DishModel dishModel);
    DishModel updateDish(Long id, DishUpdateModel dishUpdateModel);
    DishModel disableDish(Long id, Boolean status, String dniOwner);
}
