package com.foodcourt.squaremallmanagment.mocks;

import com.foodcourt.squaremallmanagment.application.dto.request.DishRequestUpdateDto;
import com.foodcourt.squaremallmanagment.application.dto.response.DishResponse;
import com.foodcourt.squaremallmanagment.domain.model.dish.DishModel;
import com.foodcourt.squaremallmanagment.domain.model.dish.DishUpdateModel;
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

    public static DishModel createDishModel (){
        return DishModel.builder()
                .dishInfo(DishModel.DishInfo.builder()
                        .name("Pizza Margarita")
                        .description("Pizza clásica con tomate y albahaca")
                        .price(12.5)
                        .imageUrl("https://dummyimage.com/pizza-margarita.jpg")
                        .idCategory(2L)
                        .build())
                .restaurantInfo(DishModel.RestaurantInfo.builder()
                        .idRestaurant(5L)
                        .build())
                .build();
    }

    public static DishRequestUpdateDto createDishRequestDto(){
        return DishRequestUpdateDto.builder()
                .description("Pizza clásica sin tomate y albahaca")
                .price(12.9)
                .build();
    }

    public static DishUpdateModel createDishUpdateModel(){
        return DishUpdateModel.builder()
                .description("Pizza clásica sin tomate y albahaca")
                .price(12.9)
                .build();
    }

    public static DishResponse createDishResponse(){

        return DishResponse.builder()
                .name("Pizza Margarita")
                .idCategory(2L)
                .description("Pizza clásica con tomate y albahaca")
                .price(12.5)
                .idRestaurant(5L)
                .imageUrl("https://dummyimage.com/pizza-margarita.jpg")
        .build();

    }
}
