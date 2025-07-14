package com.foodcourt.traceabilitymanagement.domain.spi;

import com.foodcourt.traceabilitymanagement.domain.model.restaurant.RestaurantModel;

public interface IRestaurantRestPort {

    RestaurantModel findRestaurantById(Long restaurantId);
}
