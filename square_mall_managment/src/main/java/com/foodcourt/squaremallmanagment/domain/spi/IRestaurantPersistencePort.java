package com.foodcourt.squaremallmanagment.domain.spi;

import com.foodcourt.squaremallmanagment.domain.model.RestaurantModel;

public interface IRestaurantPersistencePort {

    RestaurantModel saveRestaurant(RestaurantModel restaurantModel);
}
