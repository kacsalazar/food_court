package com.foodcourt.squaremallmanagment.infrastructure.input.rest;

import com.foodcourt.squaremallmanagment.application.dto.request.RestaurantRequestDto;
import com.foodcourt.squaremallmanagment.application.dto.response.GetRestaurantByOwnerResponse;
import com.foodcourt.squaremallmanagment.application.dto.response.RestaurantResponse;
import com.foodcourt.squaremallmanagment.application.handler.IRestaurantHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

class RestaurantRestControllerTest {


    @Mock
    private IRestaurantHandler restaurantHandler;

    @InjectMocks
    private RestaurantRestController restaurantRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveRestaurant() {
        RestaurantRequestDto dto = new RestaurantRequestDto();

        ResponseEntity<Void> response = restaurantRestController.saveRestaurant(dto);

        verify(restaurantHandler).saveRestaurant(dto);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void getAllRestaurants() {
        int page = 0, size = 10;
        List<RestaurantResponse> list = Collections.singletonList(new RestaurantResponse());
        when(restaurantHandler.getAllRestaurants(page, size)).thenReturn(list);

        ResponseEntity<List<RestaurantResponse>> response = restaurantRestController.getAllRestaurants(page, size);

        verify(restaurantHandler).getAllRestaurants(page, size);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(list, response.getBody());
    }

    @Test
    void getRestaurantByIdOwner() {
        Long idOwner = 1L;
        GetRestaurantByOwnerResponse ownerResponse = new GetRestaurantByOwnerResponse();
        when(restaurantHandler.getRestaurantByIdOwner(idOwner)).thenReturn(ownerResponse);

        ResponseEntity<GetRestaurantByOwnerResponse> response = restaurantRestController.getRestaurantByIdOwner(idOwner);

        verify(restaurantHandler).getRestaurantByIdOwner(idOwner);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ownerResponse, response.getBody());
    }
}