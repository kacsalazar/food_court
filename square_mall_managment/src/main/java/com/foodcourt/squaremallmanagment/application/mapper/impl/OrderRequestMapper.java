package com.foodcourt.squaremallmanagment.application.mapper.impl;

import com.foodcourt.squaremallmanagment.application.dto.request.OrderCreateRequest;
import com.foodcourt.squaremallmanagment.application.handler.util.UtilClass;
import com.foodcourt.squaremallmanagment.domain.model.OrderModel;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OrderRequestMapper {

    public static OrderModel toOrderModel(OrderCreateRequest orderCreateRequest) {
        return OrderModel.builder()
                .restaurantId(orderCreateRequest.getRestaurantId())
                .employeeId(orderCreateRequest.getEmployeeId())
                .userDni(UtilClass.getUserDni())
                .dishes(orderCreateRequest.getDishes().stream()
                        .map(dish -> OrderModel.Dish.builder()
                                .dishId(dish.getDishId())
                                .quantity(dish.getQuantity())
                                .build())
                        .toList())
                .build();
    }
}
