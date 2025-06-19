package com.foodcourt.squaremallmanagment.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OrderCreateRequest {

    private Long restaurantId;
    private Long employeeId;
    private List<DishRequest> dishes;

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @Builder
    public static class DishRequest {
        private Long dishId;
        private Integer quantity;

    }

}
