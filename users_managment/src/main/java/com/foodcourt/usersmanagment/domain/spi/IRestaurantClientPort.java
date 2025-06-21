package com.foodcourt.usersmanagment.domain.spi;

import com.foodcourt.usersmanagment.domain.model.RestaurantModel;

public interface IRestaurantClientPort {

    RestaurantModel getRestaurantIdByOwner(Long ownerId);
}
