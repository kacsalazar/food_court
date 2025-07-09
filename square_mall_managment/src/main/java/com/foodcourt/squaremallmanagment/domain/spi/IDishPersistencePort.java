package com.foodcourt.squaremallmanagment.domain.spi;

import com.foodcourt.squaremallmanagment.domain.model.dish.DishModel;
import com.foodcourt.squaremallmanagment.domain.model.dish.DishUpdateModel;
import com.foodcourt.squaremallmanagment.domain.model.dish.ListDishesByRestaurantModel;
import com.foodcourt.squaremallmanagment.domain.model.dish.ListDishesRetrieved;

import java.util.List;

public interface IDishPersistencePort {

    void saveDish(DishModel dishModel);
    DishModel findDishById(Long id);
    DishModel updateDish(DishModel dish, DishUpdateModel dishUpdateModel);
    DishModel disableDish(DishModel dish);
    List<ListDishesByRestaurantModel> getDishesByCategory
            (ListDishesRetrieved listDishesRetrieved);

}
