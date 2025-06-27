package com.foodcourt.squaremallmanagment.infrastructure.out.jpa.adapter;

import com.foodcourt.squaremallmanagment.CreatorMocks;
import com.foodcourt.squaremallmanagment.domain.model.restaurant.RestaurantModel;
import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.entity.RestaurantEntity;
import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.mapper.IRestaurantEntityMapper;
import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.repository.IRestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
        RestaurantModel restaurantModel = CreatorMocks.createRestaurantModel();
        RestaurantEntity restaurantEntity = CreatorMocks.createRestaurantEntity();

        when(restaurantMapper.toRestaurantEntity(restaurantModel)).thenReturn(restaurantEntity);
        when(restaurantRepository.save(restaurantEntity)).thenReturn(restaurantEntity);
        when(restaurantMapper.toRestaurantModel(restaurantEntity)).thenReturn(restaurantModel);

        RestaurantModel result = restaurantAdapter.saveRestaurant(restaurantModel);

        verify(restaurantMapper).toRestaurantEntity(restaurantModel);
        verify(restaurantRepository).save(restaurantEntity);
        verify(restaurantMapper).toRestaurantModel(restaurantEntity);
        assertEquals(restaurantModel, result);
    }

    @Test
    void findRestaurantByIdTest() {
        Long id = 1L;
        RestaurantEntity entity = CreatorMocks.createRestaurantEntity();
        RestaurantModel model = CreatorMocks.createRestaurantModel();

        when(restaurantRepository.findById(id)).thenReturn(Optional.of(entity));
        when(restaurantMapper.toRestaurantModel(entity)).thenReturn(model);

        RestaurantModel result = restaurantAdapter.findRestaurantById(id);

        assertEquals(model, result);
    }

    @Test
    void getAllRestaurantsTest() {
        int page = 0, size = 10;
        List<RestaurantEntity> entities = Collections.singletonList(CreatorMocks.createRestaurantEntity());
        List<RestaurantModel> models = Collections.singletonList(CreatorMocks.createRestaurantModel());

        when(restaurantRepository.findAllByOrderByIdAsc(page, size)).thenReturn(entities);
        when(restaurantMapper.toRestaurantModelList(entities)).thenReturn(models);

        List<RestaurantModel> result = restaurantAdapter.getAllRestaurants(page, size);

        assertEquals(models, result);
    }

    @Test
    void findRestaurantByIdOwnerTest() {
        Long idOwner = 1L;
        RestaurantEntity entity = CreatorMocks.createRestaurantEntity();
        RestaurantModel model = CreatorMocks.createRestaurantModel();

        when(restaurantRepository.findRestaurantByIdOwner(idOwner)).thenReturn(entity);
        when(restaurantMapper.toRestaurantModel(entity)).thenReturn(model);

        RestaurantModel result = restaurantAdapter.findRestaurantByIdOwner(idOwner);

        assertEquals(model, result);
    }

}