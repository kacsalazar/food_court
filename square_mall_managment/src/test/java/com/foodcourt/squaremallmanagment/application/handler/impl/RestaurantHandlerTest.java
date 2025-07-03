package com.foodcourt.squaremallmanagment.application.handler.impl;

import com.foodcourt.squaremallmanagment.application.dto.request.RestaurantRequestDto;
import com.foodcourt.squaremallmanagment.application.dto.response.GetRestaurantByOwnerResponse;
import com.foodcourt.squaremallmanagment.application.dto.response.RestaurantResponse;
import com.foodcourt.squaremallmanagment.application.mapper.IRestaurantRequestMapper;
import com.foodcourt.squaremallmanagment.domain.api.IRestaurantServicePort;
import com.foodcourt.squaremallmanagment.domain.model.restaurant.RestaurantModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

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

    @Test
    void getAllRestaurants() {
        int page = 0;
        int size = 10;
        List<RestaurantModel> modelList = List.of(new RestaurantModel());
        List<RestaurantResponse> responseList = List.of(new RestaurantResponse());

        when(restaurantServicePort.getAllRestaurants(page, size)).thenReturn(modelList);
        when(restaurantMapper.toRestaurantResponseList(modelList)).thenReturn(responseList);

        List<RestaurantResponse> result = restaurantHandler.getAllRestaurants(page, size);

        assertThat(result).isEqualTo(responseList);
        verify(restaurantServicePort).getAllRestaurants(page, size);
    }

    @Test
    void getRestaurantByIdOwner() {
        Long idOwner = 5L;
        RestaurantModel model = new RestaurantModel();
        GetRestaurantByOwnerResponse response = new GetRestaurantByOwnerResponse();

        when(restaurantServicePort.getRestaurantByIdOwner(idOwner)).thenReturn(model);
        when(restaurantMapper.toGetRestaurantByOwnerResponse(model)).thenReturn(response);

        GetRestaurantByOwnerResponse result = restaurantHandler.getRestaurantByIdOwner(idOwner);

        assertThat(result).isEqualTo(response);
        verify(restaurantServicePort).getRestaurantByIdOwner(idOwner);
    }
}