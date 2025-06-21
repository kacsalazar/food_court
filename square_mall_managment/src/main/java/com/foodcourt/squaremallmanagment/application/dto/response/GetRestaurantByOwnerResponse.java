package com.foodcourt.squaremallmanagment.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class GetRestaurantByOwnerResponse {

    private String name;
    private String urlLogo;
    private Long idOwner;
    private Long id;
}
