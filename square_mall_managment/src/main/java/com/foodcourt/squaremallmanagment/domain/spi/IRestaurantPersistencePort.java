package com.foodcourt.squaremallmanagment.domain.spi;

import com.foodcourt.squaremallmanagment.domain.model.RestaurantModel;

import java.util.List;

public interface IRestaurantPersistencePort {

    RestaurantModel saveRestaurant(RestaurantModel restaurantModel);
    RestaurantModel findRestaurantById(Long id);
    List<RestaurantModel> getAllRestaurants(Integer page, Integer size);
}
