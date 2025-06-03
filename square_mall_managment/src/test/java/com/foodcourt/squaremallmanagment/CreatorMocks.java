package com.foodcourt.squaremallmanagment;

import com.foodcourt.squaremallmanagment.application.dto.request.RestaurantRequestDto;

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
}
