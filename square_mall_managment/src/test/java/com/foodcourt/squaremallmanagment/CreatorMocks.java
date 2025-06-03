package com.foodcourt.squaremallmanagment;

import com.foodcourt.squaremallmanagment.application.dto.request.RestaurantRequestDto;
import com.foodcourt.squaremallmanagment.domain.model.RestaurantModel;
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
                .phoneNumber("555-1234")
                .urlLogo("http://example.com/logo.png")
                .nit("NIT123456").build();
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
}
