package com.foodcourt.traceabilitymanagement.infrastructure.out.restclient;

import static org.junit.jupiter.api.Assertions.*;

import com.foodcourt.traceabilitymanagement.domain.model.restaurant.RestaurantModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.*;

class RestaurantClientAdapterTest {

    private RestTemplate restTemplate;
    private RestaurantClientAdapter adapter;

    @BeforeEach
    void setUp() {
        restTemplate = mock(RestTemplate.class);
        adapter = new RestaurantClientAdapter(restTemplate);
    }

    @Test
    void findRestaurantById_shouldReturnRestaurantModel() {
        // Arrange
        Long restaurantId = 2L;
        String expectedUrl = "http://localhost:8082/api/v1/restaurant/restaurant/" + restaurantId;

        RestaurantModel mockRestaurant = new RestaurantModel();
        mockRestaurant.setId(restaurantId);
        mockRestaurant.setIdOwner(99L);

        when(restTemplate.getForObject(expectedUrl, RestaurantModel.class)).thenReturn(mockRestaurant);

        // Act
        RestaurantModel result = adapter.findRestaurantById(restaurantId);

        // Assert
        assertNotNull(result);
        assertEquals(restaurantId, result.getId());
        assertEquals(99L, result.getIdOwner());
    }

}