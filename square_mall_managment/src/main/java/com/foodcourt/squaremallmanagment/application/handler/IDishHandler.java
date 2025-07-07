package com.foodcourt.squaremallmanagment.application.handler;

import com.foodcourt.squaremallmanagment.application.dto.request.DishCreateRequest;
import com.foodcourt.squaremallmanagment.application.dto.request.DishRequestUpdateDto;
import com.foodcourt.squaremallmanagment.application.dto.response.DishResponse;
import com.foodcourt.squaremallmanagment.application.dto.response.DishRestaurantResponse;

import java.util.List;

public interface IDishHandler {

    void saveDish(DishCreateRequest dishCreateRequest);
    DishResponse updateDish(Long dishId, DishRequestUpdateDto dishRequestUpdateDto);
    DishResponse disableDish(Long dishId, Boolean status);
    List<DishRestaurantResponse> getDishesByCategory(Long idRestaurant, Long idCategory, Integer page, Integer size);
}
