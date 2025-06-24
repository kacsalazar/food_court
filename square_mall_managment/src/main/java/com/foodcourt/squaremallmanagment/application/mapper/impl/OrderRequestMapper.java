package com.foodcourt.squaremallmanagment.application.mapper.impl;

import com.foodcourt.squaremallmanagment.application.dto.request.OrderCreateRequest;
import com.foodcourt.squaremallmanagment.application.dto.response.OrderResponse;
import com.foodcourt.squaremallmanagment.application.handler.util.UtilClass;
import com.foodcourt.squaremallmanagment.domain.model.order.OrderModel;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class OrderRequestMapper {

    public static OrderModel toOrderModel(OrderCreateRequest orderCreateRequest) {
        return OrderModel.builder()
                .restaurantId(orderCreateRequest.getRestaurantId())
                .userDni(UtilClass.getUserDni())
                .dishes(orderCreateRequest.getDishes().stream()
                        .map(dish -> OrderModel.Dish.builder()
                                .dishId(dish.getDishId())
                                .quantity(dish.getQuantity())
                                .build())
                        .toList())
                .build();
    }

    public static List<OrderResponse> toOrderResponse(List<OrderModel> orderModelReturns) {
        return orderModelReturns.stream()
                .map(order -> OrderResponse.builder()
                        .restaurantId(order.getRestaurantId())
                        .employeeId(order.getEmployeeId())
                        .dishes(order.getDishes().stream()
                                .map(dish -> OrderResponse.DishResponse.builder()
                                        .dishId(dish.getDishId())
                                        .quantity(dish.getQuantity())
                                        .build())
                                .toList())
                        .userDni(order.getUserDni())
                        .status(order.getStatus())
                        .orderDate(order.getOrderDate())
                        .build())
                .toList();
    }
}
