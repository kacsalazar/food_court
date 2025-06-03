package com.foodcourt.squaremallmanagment.application.handler.impl;

import com.foodcourt.squaremallmanagment.CreatorMocks;
import com.foodcourt.squaremallmanagment.application.dto.request.RestaurantRequestDto;
import com.foodcourt.squaremallmanagment.application.mapper.IRestaurantRequestMapper;
import com.foodcourt.squaremallmanagment.domain.api.IRestaurantServicePort;
import com.foodcourt.squaremallmanagment.domain.model.RestaurantModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantHandlerTest {
    @Mock
    IRestaurantRequestMapper restaurantMapper;

    @Mock
    IRestaurantServicePort restaurantServicePort;

    @InjectMocks
    RestaurantHandler restaurantHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveRestaurant_validUser_savesRestaurant() {
        RestaurantRequestDto dto = mock(RestaurantRequestDto.class);
        RestaurantModel model = mock(RestaurantModel.class);

        when(restaurantMapper.toRestaurant(dto)).thenReturn(model);

        restaurantHandler.saveRestaurant(dto, true);

        verify(restaurantMapper, times(1)).toRestaurant(dto);
        verify(restaurantServicePort, times(1)).saveRestaurant(model);
    }

    @Test
    void saveRestaurant_invalidUser_throwsException() {
        RestaurantRequestDto dto = mock(RestaurantRequestDto.class);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            restaurantHandler.saveRestaurant(dto, false);
        });

        assertEquals("Invalid user role for restaurant creation", exception.getMessage());
        verify(restaurantMapper, never()).toRestaurant(any());
        verify(restaurantServicePort, never()).saveRestaurant(any());
    }

}