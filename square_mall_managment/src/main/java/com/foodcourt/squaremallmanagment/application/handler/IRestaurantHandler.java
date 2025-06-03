package com.foodcourt.squaremallmanagment.application.handler;

import com.foodcourt.squaremallmanagment.application.dto.request.RestaurantRequestDto;

public interface IRestaurantHandler {
    void saveRestaurant(RestaurantRequestDto restaurantRequestDto, Boolean isValidUser);
}
