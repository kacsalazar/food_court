package com.foodcourt.squaremallmanagment.infrastructure.input.rest;

import com.foodcourt.squaremallmanagment.application.dto.request.RestaurantRequestDto;
import com.foodcourt.squaremallmanagment.application.dto.response.GetRestaurantByOwnerResponse;
import com.foodcourt.squaremallmanagment.application.dto.response.RestaurantByIdResponse;
import com.foodcourt.squaremallmanagment.application.dto.response.RestaurantResponse;
import com.foodcourt.squaremallmanagment.application.handler.IRestaurantHandler;

import com.foodcourt.squaremallmanagment.infrastructure.input.rest.util.SecurityExpressions;
import lombok.RequiredArgsConstructor;
import com.foodcourt.squaremallmanagment.infrastructure.documentation.IRestaurantRestController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/restaurant")
public class RestaurantRestController implements IRestaurantRestController {

    private final IRestaurantHandler restaurantHandler;

    @PreAuthorize(SecurityExpressions.OWNER)
    @PostMapping("/")
    public ResponseEntity<Void> saveRestaurant(@RequestBody RestaurantRequestDto restaurantRequestDto) {
        restaurantHandler.saveRestaurant(restaurantRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize(SecurityExpressions.CUSTOMER)
    @GetMapping("/restaurants")
    public ResponseEntity<List<RestaurantResponse>> getAllRestaurants( @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        return ResponseEntity.ok( restaurantHandler.getAllRestaurants(page, size));
    }

    @GetMapping("/ownerId/{idOwner}")
    public ResponseEntity<GetRestaurantByOwnerResponse> getRestaurantByIdOwner(@PathVariable Long idOwner) {
        return ResponseEntity.ok( restaurantHandler.getRestaurantByIdOwner(idOwner));
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<RestaurantByIdResponse> findRestaurantById(@PathVariable Long restaurantId) {
        return ResponseEntity.ok( restaurantHandler.findRestaurantById(restaurantId));
    }
}
