package com.foodcourt.squaremallmanagment.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RestaurantModel {

    private Long id;
    private String name;
    private String address;
    private Long id_owner;
    private String phoneNumber;
    private String urlLogo;
    private String nit;
}
