package com.foodcourt.squaremallmanagment.domain.spi;

import com.foodcourt.squaremallmanagment.domain.model.DishModel;
import com.foodcourt.squaremallmanagment.domain.model.DishUpdateModel;
import com.foodcourt.squaremallmanagment.domain.model.ListDishesByRestaurantModel;

import java.util.List;

public interface IDishPersistencePort {

    void saveDish(DishModel dishModel);
    DishModel findDishById(Long id);
    DishModel updateDish(Long id, DishUpdateModel dishUpdateModel);
    DishModel disableDish(Long id, Boolean status);
    List<ListDishesByRestaurantModel> getDishesByCategory(Long idRestaurant, Long idCategory, Integer page, Integer size);

}
