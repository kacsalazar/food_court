package com.foodcourt.squaremallmanagment.domain.usecase;

import com.foodcourt.squaremallmanagment.domain.model.RestaurantModel;
import com.foodcourt.squaremallmanagment.domain.spi.IRestaurantPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class RestaurantUseCaseTest {

    @Mock
    IRestaurantPersistencePort restaurantPersistencePort;

    @InjectMocks
    RestaurantUseCase restaurantUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveRestaurantTest() {
        RestaurantModel model = mock(RestaurantModel.class);

        restaurantUseCase.saveRestaurant(model);

        verify(restaurantPersistencePort, times(1)).saveRestaurant(model);
    }

}