package com.foodcourt.squaremallmanagment.infrastructure.input.rest;

import com.foodcourt.squaremallmanagment.CreatorMocks;
import com.foodcourt.squaremallmanagment.application.dto.request.DishCreateRequest;
import com.foodcourt.squaremallmanagment.application.dto.request.DishRequestUpdateDto;
import com.foodcourt.squaremallmanagment.application.dto.response.DishResponse;
import com.foodcourt.squaremallmanagment.application.handler.IDishHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
        DishCreateRequest dto = CreatorMocks.createDishRequestDto();

        ResponseEntity<Void> response = controller.saveDish(dto);

        verify(dishHandler).saveDish(dto);
        assert response.getStatusCode() == HttpStatus.CREATED;
        assert response.getBody() == null;
    }

    void updateDish() {
        Long id = 1L;
        DishRequestUpdateDto updateDto = CreatorMocks.createDishRequestUpdateDto();
        DishResponse responseDto = CreatorMocks.createDishResponseDto();

        when(dishHandler.updateDish(id, updateDto)).thenReturn(responseDto);

        ResponseEntity<DishResponse> response = controller.updateDish(id, updateDto);

        verify(dishHandler).updateDish(id, updateDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDto, response.getBody());
    }
}