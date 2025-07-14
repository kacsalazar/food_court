package com.foodcourt.squaremallmanagment.application.dto.response;


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
public class OrderResponse {

    private Long orderId;
    private Long restaurantId;
    private Long employeeId;
    private List<DishResponse> dishes;
    private String userDni;
    private String status;
    private Date orderDate;

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @Builder
    public static class DishResponse {
        private Long dishId;
        private Integer quantity;

    }
}
