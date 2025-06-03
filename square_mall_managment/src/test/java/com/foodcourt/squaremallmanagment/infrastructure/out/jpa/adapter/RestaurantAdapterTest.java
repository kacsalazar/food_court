package com.foodcourt.squaremallmanagment.infrastructure.out.jpa.adapter;

import com.foodcourt.squaremallmanagment.domain.model.RestaurantModel;
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
import static org.mockito.Mockito.verify;

class RestaurantAdapterTest {


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
        RestaurantModel inputModel = mock(RestaurantModel.class);
        RestaurantEntity entity = mock(RestaurantEntity.class);
        RestaurantModel outputModel = mock(RestaurantModel.class);

        when(restaurantMapper.toRestaurantEntity(inputModel)).thenReturn(entity);
        when(restaurantMapper.toRestaurantModel(entity)).thenReturn(outputModel);

        RestaurantModel result = restaurantAdapter.saveRestaurant(inputModel);

        verify(restaurantMapper).toRestaurantEntity(inputModel);
        verify(restaurantRepository).save(entity);
        verify(restaurantMapper).toRestaurantModel(entity);
        assertEquals(outputModel, result);
    }

}