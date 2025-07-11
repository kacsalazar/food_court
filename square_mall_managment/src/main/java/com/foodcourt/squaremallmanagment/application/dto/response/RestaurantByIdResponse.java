package com.foodcourt.squaremallmanagment.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RestaurantByIdResponse {
    private Long id;
    private String name;
    private String address;
    private Long idOwner;
    private String phoneNumber;
    private String urlLogo;
    private String nit;
}
