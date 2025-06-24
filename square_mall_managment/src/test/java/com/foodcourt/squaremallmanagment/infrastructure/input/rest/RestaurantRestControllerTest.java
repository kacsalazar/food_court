package com.foodcourt.squaremallmanagment.infrastructure.input.rest;

import com.foodcourt.squaremallmanagment.CreatorMocks;
import com.foodcourt.squaremallmanagment.domain.model.restaurant.RestaurantModel;
import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.adapter.RestaurantAdapter;
import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.entity.RestaurantEntity;
import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.mapper.IRestaurantEntityMapper;
import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.repository.IRestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

class RestaurantRestControllerTest {


    @Mock
    IRestaurantRepository restaurantRepository;

    @Mock
    IRestaurantEntityMapper restaurantMapper;

    @InjectMocks
    RestaurantAdapter restaurantAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveRestaurantTest() {
        RestaurantModel restaurantModel = CreatorMocks.createRestaurantModel();
        RestaurantEntity restaurantEntity = mock(RestaurantEntity.class);


        when(restaurantMapper.toRestaurantEntity(restaurantModel)).thenReturn(restaurantEntity);
        when(restaurantMapper.toRestaurantModel(restaurantEntity)).thenReturn(restaurantModel);

        RestaurantModel result = restaurantAdapter.saveRestaurant(restaurantModel);

        verify(restaurantMapper).toRestaurantEntity(restaurantModel);
        verify(restaurantRepository).save(restaurantEntity);
        verify(restaurantMapper).toRestaurantModel(restaurantEntity);
        assertEquals(restaurantModel, result);
    }
}