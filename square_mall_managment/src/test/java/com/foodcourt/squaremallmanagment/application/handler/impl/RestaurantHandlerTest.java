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
    IRestaurantRequestMapper restaurantMapper;

    @Mock
    IRestaurantServicePort restaurantServicePort;

    @Mock
    IUserClientServicePort userClientServicePort;

    @InjectMocks
    RestaurantHandler restaurantHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveRestaurant_usuarioValido_guardaRestaurante() {
        RestaurantRequestDto dto = new RestaurantRequestDto();
        dto.setIdOwner(1L);
        dto.setName("Restaurante Prueba");
        dto.setAddress("Calle 123");
        // ... setea otros campos si es necesario

        RestaurantModel model = new RestaurantModel();
        model.setIdOwner(1L);
        model.setName("Restaurante Prueba");
        model.setAddress("Calle 123");
        // ... setea otros campos si es necesario

        when(userClientServicePort.isValidUser("1", "ROLE_OWNER")).thenReturn(true);
        when(restaurantMapper.toRestaurantModel(dto)).thenReturn(model);

        restaurantHandler.saveRestaurant(dto);

        verify(userClientServicePort).isValidUser("1", "ROLE_OWNER");
        verify(restaurantMapper).toRestaurantModel(dto);
        verify(restaurantServicePort).saveRestaurant(model);
    }

    @Test
    void saveRestaurant_usuarioInvalido_lanzaExcepcion() {
        RestaurantRequestDto dto = new RestaurantRequestDto();
        dto.setIdOwner(2L);

        when(userClientServicePort.isValidUser("2L", "ROLE_OWNER")).thenReturn(false);

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            restaurantHandler.saveRestaurant(dto);
        });

        assertEquals("Invalid user role for restaurant creation", ex.getMessage());
        verify(userClientServicePort).isValidUser("2L", "ROLE_OWNER");
        verify(restaurantMapper, never()).toRestaurantModel(any());
        verify(restaurantServicePort, never()).saveRestaurant(any());
    }

}