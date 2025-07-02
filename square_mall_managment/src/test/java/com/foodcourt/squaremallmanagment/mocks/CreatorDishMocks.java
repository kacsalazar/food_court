package com.foodcourt.squaremallmanagment.mocks;

import com.foodcourt.squaremallmanagment.domain.model.dish.DishModel;
import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.entity.DishEntity;

public class CreatorDishMocks {

    public static DishModel buildCompleteDishModel() {
        return DishModel.builder()
                .dishInfo(DishModel.DishInfo.builder()

                        .name("Pizza")
                        .description("Delicious cheese pizza")
                        .price(15.99)
                        .imageUrl("https://example.com/pizza.jpg")
                        .idCategory(5L)
                        .isActive(false)
                        .build())
                .restaurantInfo(DishModel.RestaurantInfo.builder()
                        .idRestaurant(1L)
                        .build())
                .build();
    }

    public static DishEntity buildCompleteDishEntity() {
        return DishEntity.builder()
                .id(1L)
                .name("Pizza")
                .description("Delicious cheese pizza")
                .price(Double.valueOf(15.99))
                .imageUrl("https://example.com/pizza.jpg")
                .idRestaurant(10L)
                .idCategory(5L)
                .isActive(true)
                .build();
    }
}
