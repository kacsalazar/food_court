package com.foodcourt.squaremallmanagment.mocks;

import com.foodcourt.squaremallmanagment.domain.model.order.OrderModel;

import java.util.List;

public class CreatorAdapterMocks {

    public static OrderModel createOrderModel(){
        return OrderModel.builder()
                .id(1L)
                .userDni("123456789")
                .restaurantId(1L)
                .employeeId(1L)
                .status("PENDING")
                .dishes(List.of(OrderModel.Dish.builder()
                                .dishId(1L)
                                .quantity(2)
                                .build())
                ).orderDate(null).build();
    }


}
