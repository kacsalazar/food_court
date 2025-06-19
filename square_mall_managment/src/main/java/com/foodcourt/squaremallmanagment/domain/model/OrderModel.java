package com.foodcourt.squaremallmanagment.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OrderModel {

    private Long restaurantId;
    private Long employeeId;
    private List<Dish> dishes;
    private String userDni;

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @Builder
    public static class Dish{

        private Long dishId;
        private Integer quantity;

    }


}
