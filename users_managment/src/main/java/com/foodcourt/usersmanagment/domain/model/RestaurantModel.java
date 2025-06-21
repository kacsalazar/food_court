package com.foodcourt.usersmanagment.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RestaurantModel {

    private String name;
    private String ownerDni;
    private Long id;
    private String address;
    private String phoneNumber;
}
