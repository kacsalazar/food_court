package com.foodcourt.squaremallmanagment.infrastructure.out.jpa.mapper.impl;

import com.foodcourt.squaremallmanagment.application.dto.response.OrderResponse;
import com.foodcourt.squaremallmanagment.domain.model.order.OrderModel;
import com.foodcourt.squaremallmanagment.domain.model.order.OrderModelReturn;
import com.foodcourt.squaremallmanagment.domain.model.order.OrderUpdateModel;
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

    public static OrderUpdateModel toOrderUpdateModel(OrderEntity orderEntity) {
        return OrderUpdateModel.builder()
                .id(orderEntity.getId())
                .idRestaurant(orderEntity.getIdRestaurant())
                .idChef(orderEntity.getIdChef())
                .status(orderEntity.getStatus())
                .idClient(orderEntity.getIdClient())
                .build();
    }

    public static OrderEntity toOrderEntityUpdate(OrderUpdateModel orderModel) {
        return OrderEntity.builder()
                .id(orderModel.getId())
                .orderDate(new Date())
                .idClient(orderModel.getIdClient())
                .status(orderModel.getStatus())
                .idChef(orderModel.getIdChef())
                .idRestaurant(orderModel.getIdRestaurant())
                .build();
    }

    /*public static List<OrderModel> toOrderModel(List<OrderEntity> orderEntities, List<OrderVsDishEntity> orderVsDishEntities) {
        return orderEntities.stream()
                .map(order -> OrderModel.builder()
                        .restaurantId(order.getIdRestaurant())
                        .employeeId(order.getIdChef())
                        .status(order.getStatus())
                        .orderDate(order.getOrderDate())
                        .dishes(orderVsDishEntities.stream()
                                .filter(dish -> dish.getIdOrder().equals(order.getId()))
                                .map(dish -> OrderModel.Dish.builder()
                                        .dishId(dish.getIdDish())
                                        .quantity(dish.getQuantity())
                                        .build())
                                .toList())
                        .build())
                .toList();
    }*/

    public static OrderModel toOrderModel(OrderEntity orderEntity, List<OrderVsDishEntity> orderVsDishEntities) {
        return OrderModel.builder()
                .restaurantId(orderEntity.getIdRestaurant())
                .employeeId(orderEntity.getIdChef())
                .status(orderEntity.getStatus())
                .orderDate(orderEntity.getOrderDate())
                .dishes(orderVsDishEntities.stream()
                        .filter(dish -> dish.getIdOrder().equals(orderEntity.getId()))
                        .map(dish -> OrderModel.Dish.builder()
                                .dishId(dish.getIdDish())
                                .quantity(dish.getQuantity())
                                .build())
                        .toList())
                .build();
    }



}
