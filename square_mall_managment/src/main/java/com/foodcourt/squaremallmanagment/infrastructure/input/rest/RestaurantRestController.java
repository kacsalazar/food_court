package com.foodcourt.squaremallmanagment.infrastructure.input.rest;

import com.foodcourt.squaremallmanagment.application.dto.request.RestaurantRequestDto;
import com.foodcourt.squaremallmanagment.application.handler.IRestaurantHandler;
import com.foodcourt.squaremallmanagment.infrastructure.configuration.UserClient;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/restaurant")
public class RestaurantRestController {

    private final IRestaurantHandler restaurantHandler;
    private final UserClient userClient;

    @PostMapping("/")
    public ResponseEntity<Void> saveRestaurant(@RequestBody RestaurantRequestDto restaurantRequestDto) {
        restaurantHandler.saveRestaurant(restaurantRequestDto, userClient.verifyUserRol(restaurantRequestDto.getIdOwner(), "ROLE_OWNER"));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
