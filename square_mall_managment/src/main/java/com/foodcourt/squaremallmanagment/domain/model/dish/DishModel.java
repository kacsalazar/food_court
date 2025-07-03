package com.foodcourt.squaremallmanagment.domain.model.dish;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DishModel {

    private DishInfo dishInfo;
    private RestaurantInfo restaurantInfo;

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @Builder
    public static class DishInfo {

        private Long id;
        private String name;
        private Long idCategory;
        private String description;
        private Double price;
        private String imageUrl;
        private Boolean isActive; // Default value set to true
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @Builder
    public static class RestaurantInfo {
        private Long idRestaurant;
    }
}
