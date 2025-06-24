package com.foodcourt.squaremallmanagment;

import com.foodcourt.squaremallmanagment.application.dto.request.DishCreateRequest;
import com.foodcourt.squaremallmanagment.application.dto.request.DishRequestUpdateDto;
import com.foodcourt.squaremallmanagment.application.dto.request.RestaurantRequestDto;
import com.foodcourt.squaremallmanagment.application.dto.response.DishResponse;
import com.foodcourt.squaremallmanagment.domain.model.dish.DishModel;
import com.foodcourt.squaremallmanagment.domain.model.dish.DishUpdateModel;
import com.foodcourt.squaremallmanagment.domain.model.restaurant.RestaurantModel;
import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.entity.DishEntity;
import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.entity.RestaurantEntity;

public class CreatorMocks {

    public static RestaurantRequestDto createRestaurantRequestDto() {
        return RestaurantRequestDto.builder()
                .name("Pizza Place")
                .address("123 Main St")
                .idOwner(1L)
                .phoneNumber("555-1234")
                .urlLogo("http://example.com/logo.png")
                .nit("NIT123456").build();
    }

    public static RestaurantModel createRestaurantModel() {
        return RestaurantModel.builder()
                .id(1L)
                .name("Pizza Place")
                .address("123 Main St")
                .idOwner(1L)
                .phoneNumber("5551234")
                .urlLogo("http://example.com/logo.png")
                .nit("123456").build();
    }

    public static RestaurantEntity createRestaurantEntity() {
        return RestaurantEntity.builder()
                .id(1L)
                .name("Pizza Place")
                .address("123 Main St")
                .idOwner(1L)
                .phoneNumber("555-1234")
                .urlLogo("http://example.com/logo.png")
                .nit("NIT123456").build();
    }

    public static DishEntity createDishEntity(){
        return DishEntity.builder()
                .id(1L)
                .name("Pizza")
                .idCategory(1L)
                .description("Delicious pizza")
                .price(10.0)
                .idRestaurant(2L)
                .imageUrl("http://example.com/pizza.png")
                .isActive(true).build();
    }

    public static DishModel createDishModel() {
        return DishModel.builder()
                .name("Pizza")
                .idCategory(1L)
                .description("Delicious pizza")
                .price(10.0)
                .idRestaurant(2L)
                .imageUrl("http://example.com/pizza.png")
                .build();
    }

    public static DishCreateRequest createDishRequestDto() {
        return DishCreateRequest.builder()
                .name("Pizza")
                .idCategory(1L)
                .description("Delicious pizza")
                .price(10.0)
                .idRestaurant(2L)
                .imageUrl("http://example.com/pizza.png")
                .build();
    }

    public static DishRequestUpdateDto createDishRequestUpdateDto() {
        return DishRequestUpdateDto.builder()
                .description("Updated delicious pizza")
                .price(12.0)
                .build();
    }

    public static DishResponse createDishResponseDto() {
        return DishResponse.builder()
                .name("Pizza")
                .idCategory(1L)
                .description("Delicious pizza")
                .price(10.0)
                .idRestaurant(2L)
                .imageUrl("http://example.com/pizza.png")
                .build();

    }

    public static DishUpdateModel createDishUpdateModel() {
        return DishUpdateModel.builder()
                .description("Updated delicious pizza")
                .price(12.0)
                .build();
    }
}
