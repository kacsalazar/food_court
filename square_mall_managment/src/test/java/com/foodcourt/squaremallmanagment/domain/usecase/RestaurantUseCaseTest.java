package com.foodcourt.squaremallmanagment.domain.usecase;

import com.foodcourt.squaremallmanagment.CreatorMocks;
import com.foodcourt.squaremallmanagment.domain.model.restaurant.RestaurantModel;
import com.foodcourt.squaremallmanagment.domain.spi.IRestaurantPersistencePort;
import com.foodcourt.squaremallmanagment.mocks.CreatorMocksRestaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

class RestaurantUseCaseTest {


    @Mock
    private IRestaurantPersistencePort restaurantPersistencePort;

    @InjectMocks
    private RestaurantUseCase restaurantUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveRestaurant() {
        // Arrange
        RestaurantModel restaurant = CreatorMocksRestaurant.createRestaurant();

        // Act
        restaurantUseCase.saveRestaurant(restaurant);

        // Assert
        verify(restaurantPersistencePort).saveRestaurant(restaurant);;
    }

    @Test
    void getAllRestaurants() {
        // Arrange
        List<RestaurantModel> restaurants = List.of(
                RestaurantModel.builder().id(1L).name("A").build(),
                RestaurantModel.builder().id(2L).name("B").build()
        );

        when(restaurantPersistencePort.getAllRestaurants(0, 2)).thenReturn(restaurants);

        // Act
        List<RestaurantModel> result = restaurantUseCase.getAllRestaurants(0, 2);

        // Assert
        assertThat(result).hasSize(2);
        verify(restaurantPersistencePort).getAllRestaurants(0, 2);
    }

    @Test
    void getRestaurantByIdOwner() {
        // Arrange
        RestaurantModel restaurant = RestaurantModel.builder()
                .id(1L)
                .idOwner(10L)
                .name("Owned Resto")
                .build();

        when(restaurantPersistencePort.findRestaurantByIdOwner(10L)).thenReturn(restaurant);

        // Act
        RestaurantModel result = restaurantUseCase.getRestaurantByIdOwner(10L);

        // Assert
        assertThat(result).isEqualTo(restaurant);
        verify(restaurantPersistencePort).findRestaurantByIdOwner(10L);
    }
}