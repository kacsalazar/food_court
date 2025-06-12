package com.foodcourt.squaremallmanagment.application.handler.impl;

import com.foodcourt.squaremallmanagment.application.dto.request.RestaurantRequestDto;
import com.foodcourt.squaremallmanagment.application.mapper.IRestaurantRequestMapper;
import com.foodcourt.squaremallmanagment.domain.api.IRestaurantServicePort;
import com.foodcourt.squaremallmanagment.domain.api.IUserClientServicePort;
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
    private IRestaurantRequestMapper restaurantMapper;

    @Mock
    private IRestaurantServicePort restaurantServicePort;

    @InjectMocks
    private RestaurantHandler restaurantHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveRestaurant() {
        RestaurantRequestDto dto = mock(RestaurantRequestDto.class);
        RestaurantModel model = mock(RestaurantModel.class);

        when(restaurantMapper.toRestaurantModel(dto)).thenReturn(model);

        restaurantHandler.saveRestaurant(dto);

        verify(restaurantMapper).toRestaurantModel(dto);
        verify(restaurantServicePort).saveRestaurant(model);
    }
}