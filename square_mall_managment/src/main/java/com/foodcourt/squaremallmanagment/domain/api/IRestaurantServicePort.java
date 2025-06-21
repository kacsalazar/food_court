package com.foodcourt.squaremallmanagment.domain.api;

import com.foodcourt.squaremallmanagment.domain.model.RestaurantModel;

import java.util.List;

public interface IRestaurantServicePort {

    void saveRestaurant(RestaurantModel restaurantModel);
    List<RestaurantModel> getAllRestaurants(Integer page, Integer size);
    RestaurantModel getRestaurantByIdOwner(Long idOwner);
}
