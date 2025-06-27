package com.foodcourt.squaremallmanagment.mocks;

import com.foodcourt.squaremallmanagment.domain.model.dish.ListDishesByRestaurantModel;
import com.foodcourt.squaremallmanagment.domain.model.restaurant.RestaurantModel;

public class CreatorMocksRestaurant {

    public static RestaurantModel createRestaurantModel() {
        return RestaurantModel.builder()
                .id(1L)
                .name("Test Restaurant")
                .address("123 Test St")
                .idOwner(1L)
                .phoneNumber("123456789")
                .urlLogo("http://example.com/logo.png")
                .nit("123456789")
                .build();
    }

    public static ListDishesByRestaurantModel createListDishesByRestaurantModel() {
        return ListDishesByRestaurantModel.builder()
                .name("Test Dish")
                .description("Delicious test dish")
                .imageUrl("http://example.com/dish.png")
                .build();
    }

    public static RestaurantModel createRestaurant(){
        return RestaurantModel.builder()
                .id(1L)
                .name("Testaurant")
                .nit("123456789")
                .address("Street 123")
                .phoneNumber("3001234567")
                .urlLogo("http://image.com/logo.png")
                .idOwner(10L)
                .build();
    }
}
