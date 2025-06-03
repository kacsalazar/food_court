package com.foodcourt.squaremallmanagment.infrastructure.input.rest;

import com.foodcourt.squaremallmanagment.CreatorMocks;
import com.foodcourt.squaremallmanagment.application.dto.request.RestaurantRequestDto;
import com.foodcourt.squaremallmanagment.application.handler.IRestaurantHandler;
import com.foodcourt.squaremallmanagment.infrastructure.configuration.UserClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

class RestaurantRestControllerTest {

    @Mock
    IRestaurantHandler restaurantHandler;

    @Mock
    UserClient userClient;

    @InjectMocks
    RestaurantRestController restaurantRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveRestaurant_callsHandlerAndReturnsCreated() {
        RestaurantRequestDto dto = mock(RestaurantRequestDto.class);
        Long ownerId = 1L;
        when(dto.getIdOwner()).thenReturn(ownerId);
        when(userClient.verifyUserRol(ownerId, "ROLE_OWNER")).thenReturn(true);

        ResponseEntity<Void> response = restaurantRestController.saveRestaurant(dto);

        verify(userClient, times(1)).verifyUserRol(ownerId, "ROLE_OWNER");
        verify(restaurantHandler, times(1)).saveRestaurant(dto, true);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
}