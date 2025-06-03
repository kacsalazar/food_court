package com.foodcourt.squaremallmanagment.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RestaurantRequestDto {

    private String name;
    private String address;
    private Long idOwner;
    private String phoneNumber;
    private String urlLogo;
    private String nit;
}
