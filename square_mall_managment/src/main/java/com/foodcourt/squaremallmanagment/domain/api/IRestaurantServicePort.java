package com.foodcourt.squaremallmanagment.domain.api;

import com.foodcourt.squaremallmanagment.domain.model.RestaurantModel;

public interface IRestaurantServicePort {

    void saveRestaurant(RestaurantModel restaurantModel);
}
