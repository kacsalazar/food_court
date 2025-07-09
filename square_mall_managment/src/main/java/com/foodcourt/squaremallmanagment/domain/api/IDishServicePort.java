package com.foodcourt.squaremallmanagment.domain.api;

import com.foodcourt.squaremallmanagment.domain.model.dish.DishModel;
import com.foodcourt.squaremallmanagment.domain.model.dish.DishUpdateModel;
import com.foodcourt.squaremallmanagment.domain.model.dish.ListDishesByRestaurantModel;
import com.foodcourt.squaremallmanagment.domain.model.dish.ListDishesRetrieved;

import java.util.List;

public interface IDishServicePort {

    void saveDish(DishModel dishModel, String dniOwner);
    DishModel updateDish(Long dishId, DishUpdateModel dishUpdateModel, String dniOwner);
    DishModel disableDish(Long dishId, Boolean status, String dniOwner);
    List<ListDishesByRestaurantModel>
    getDishesByCategory(ListDishesRetrieved listDishesRetrieved);
}
