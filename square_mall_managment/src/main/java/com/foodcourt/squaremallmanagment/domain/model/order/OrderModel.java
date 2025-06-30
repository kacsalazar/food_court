package com.foodcourt.squaremallmanagment.domain.model.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OrderModel {

    private Long id;
    private Long restaurantId;
    private Long employeeId;
    private List<Dish> dishes;
    private String userDni;
    private String status;
    private Date orderDate;

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @Builder
    public static class Dish{

        private Long dishId;
        private Integer quantity;

    }
}
