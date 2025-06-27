package com.foodcourt.squaremallmanagment.infrastructure.input.rest;

import com.foodcourt.squaremallmanagment.application.dto.request.DishCreateRequest;
import com.foodcourt.squaremallmanagment.application.dto.request.DishRequestUpdateDto;
import com.foodcourt.squaremallmanagment.application.dto.request.DishStatusRequest;
import com.foodcourt.squaremallmanagment.application.dto.response.DishResponse;
import com.foodcourt.squaremallmanagment.application.dto.response.DishRestaurantResponse;
import com.foodcourt.squaremallmanagment.application.handler.IDishHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DishRestControllerTest {


    @Mock
    private IDishHandler dishHandler;

    @InjectMocks
    private DishRestController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveDish() {
        DishCreateRequest dto = new DishCreateRequest();
        ResponseEntity<Void> response = controller.saveDish(dto);
        verify(dishHandler).saveDish(dto);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void updateDish() {
        Long id = 1L;
        DishRequestUpdateDto updateDto = new DishRequestUpdateDto();
        DishResponse responseDto = new DishResponse();
        when(dishHandler.updateDish(id, updateDto)).thenReturn(responseDto);

        ResponseEntity<DishResponse> response = controller.updateDish(id, updateDto);

        verify(dishHandler).updateDish(id, updateDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDto, response.getBody());
    }

    @Test
    void disableDish() {
        Long id = 1L;
        DishStatusRequest statusRequest = new DishStatusRequest();
        statusRequest.setStatus(false);
        DishResponse responseDto = new DishResponse();
        when(dishHandler.disableDish(id, statusRequest.getStatus())).thenReturn(responseDto);

        ResponseEntity<DishResponse> response = controller.disableDish(id, statusRequest);

        verify(dishHandler).disableDish(id, statusRequest.getStatus());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDto, response.getBody());
    }

    @Test
    void getDishesByCategory() {
        Long idRestaurant = 1L;
        Long idCategory = 2L;
        int page = 0, size = 10;
        List<DishRestaurantResponse> list = Collections.singletonList(new DishRestaurantResponse());
        when(dishHandler.getDishesByCategory(idRestaurant, idCategory, page, size)).thenReturn(list);

        ResponseEntity<List<DishRestaurantResponse>> response = controller.getDishesByCategory(idCategory, idRestaurant, page, size);

        verify(dishHandler).getDishesByCategory(idRestaurant, idCategory, page, size);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(list, response.getBody());
    }
}