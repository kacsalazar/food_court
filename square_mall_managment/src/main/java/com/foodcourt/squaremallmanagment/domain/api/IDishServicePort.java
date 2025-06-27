package com.foodcourt.squaremallmanagment.domain.api;

import com.foodcourt.squaremallmanagment.domain.model.dish.DishModel;
import com.foodcourt.squaremallmanagment.domain.model.dish.DishUpdateModel;
import com.foodcourt.squaremallmanagment.domain.model.dish.ListDishesByRestaurantModel;

import java.util.List;

public interface IDishServicePort {

    void saveDish(DishModel dishModel, String dniOwner);
    DishModel updateDish(Long id, DishUpdateModel dishUpdateModel, String dniOwner);
    DishModel disableDish(Long id, Boolean status, String dniOwner);
    List<ListDishesByRestaurantModel>
    getDishesByCategory(Long idRestaurant, Long idCategory, Integer page, Integer size);
}
