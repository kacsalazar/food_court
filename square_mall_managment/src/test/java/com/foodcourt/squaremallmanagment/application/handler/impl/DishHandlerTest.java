package com.foodcourt.squaremallmanagment.application.handler.impl;

import com.foodcourt.squaremallmanagment.CreatorMocks;
import com.foodcourt.squaremallmanagment.application.dto.request.DishRequestDto;
import com.foodcourt.squaremallmanagment.application.dto.request.DishRequestUpdateDto;
import com.foodcourt.squaremallmanagment.application.dto.response.DishResponseDto;
import com.foodcourt.squaremallmanagment.application.mapper.IDishRequestMapper;
import com.foodcourt.squaremallmanagment.domain.api.IDishServicePort;
import com.foodcourt.squaremallmanagment.domain.model.DishModel;
import com.foodcourt.squaremallmanagment.domain.model.DishUpdateModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DishHandlerTest {


    @Mock
    private IDishRequestMapper dishMapper;

    @Mock
    private IDishServicePort dishServicePort;

    @InjectMocks
    private DishHandler dishHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void saveDish() {
        DishRequestDto dto = CreatorMocks.createDishRequestDto();
        DishModel model = CreatorMocks.createDishModel();

        when(dishMapper.toDishModel(dto)).thenReturn(model);

        dishHandler.saveDish(dto);

        verify(dishMapper).toDishModel(dto);
        verify(dishServicePort).saveDish(model);
    }

    void updateDish() {
        Long id = 1L;
        DishRequestUpdateDto requestUpdateDto = CreatorMocks.createDishRequestUpdateDto();
        DishUpdateModel updateModel = CreatorMocks.createDishUpdateModel();
        DishModel dishModel = CreatorMocks.createDishModel();
        DishResponseDto responseDto = CreatorMocks.createDishResponseDto();

        when(dishMapper.toDishUpdateModel(requestUpdateDto)).thenReturn(updateModel);
        when(dishServicePort.updateDish(id, updateModel)).thenReturn(dishModel);
        when(dishMapper.toDishResponseDto(dishModel)).thenReturn(responseDto);

        DishResponseDto result = dishHandler.updateDish(id, requestUpdateDto);

        verify(dishMapper).toDishUpdateModel(requestUpdateDto);
        verify(dishServicePort).updateDish(id, updateModel);
        verify(dishMapper).toDishResponseDto(dishModel);
        assertEquals(responseDto, result);
    }

}