package com.foodcourt.squaremallmanagment.mocks;

import com.foodcourt.squaremallmanagment.domain.model.order.OrderModel;
import com.foodcourt.squaremallmanagment.domain.model.order.OrderUpdateModel;

import java.util.List;

public class CreatorOrderMocks {

    public static OrderModel createOrderModel(){
        return OrderModel.builder()
                .id(1L)
                .restaurantId(1L)
                .employeeId(1L)
                .dishes(List.of(
                        new OrderModel.Dish(1L, 2),
                        new OrderModel.Dish(2L, 3)
                ))
                .userDni("123")
                .status("PENDING")
                .build();
    }

    public static OrderUpdateModel buildPendingOrderUpdateModel() {
        return OrderUpdateModel.builder()
                .id(1L)
                .idClient(10L)
                .status("PENDING")
                .build();
    }


    public static OrderUpdateModel buildOrderUpdateModelWithStatus(String status) {
        return OrderUpdateModel.builder()
                .id(1L)
                .idClient(10L)
                .status(status)
                .idChef(2L)
                .build();
    }
}
