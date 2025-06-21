package com.foodcourt.squaremallmanagment.infrastructure.out.jpa.mapper.impl;

import com.foodcourt.squaremallmanagment.domain.model.OrderModel;
import com.foodcourt.squaremallmanagment.domain.model.OrderModelReturn;
import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.entity.OrderVsDishEntity;
import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.entity.OrderEntity;
import lombok.experimental.UtilityClass;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@UtilityClass
public class OrderEntityMapper {

    public static OrderEntity toOrderEntity(OrderModel orderModel) {
        return OrderEntity.builder()
                .orderDate(new Date())
                .status(orderModel.getStatus())
                .idChef(orderModel.getEmployeeId())
                .idRestaurant(orderModel.getRestaurantId())
                .build();
    }

    public static OrderVsDishEntity toOrderDishEntity(OrderModel.Dish dish, Long orderId) {
        return OrderVsDishEntity.builder()
                .idDish(dish.getDishId())
                .quantity(dish.getQuantity())
                .idOrder(orderId)
                .build();
    }

    public static List<OrderModelReturn> toOrderModelReturn(List<OrderEntity> orderEntity) {
        return orderEntity.stream()
                .map(order -> OrderModelReturn.builder()
                        .orderDate(order.getOrderDate().toInstant()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate())
                        .status(order.getStatus())
                        .restaurantId(order.getIdRestaurant())
                        .employeeId(order.getIdChef())
                        .build())
                .toList();
    }
}
