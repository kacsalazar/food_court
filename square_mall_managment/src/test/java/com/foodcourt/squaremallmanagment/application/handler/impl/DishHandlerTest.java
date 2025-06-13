package com.foodcourt.squaremallmanagment.application.handler.impl;

import com.foodcourt.squaremallmanagment.application.dto.request.DishCreateRequest;
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
import static org.mockito.Mockito.*;

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
        DishCreateRequest dto = mock(DishCreateRequest.class);
        DishModel model = mock(DishModel.class);

        when(dishMapper.toDishModel(dto)).thenReturn(model);

        dishHandler.saveDish(dto);

        verify(dishMapper).toDishModel(dto);
        verify(dishServicePort).saveDish(model);
    }

    @Test
    void updateDish() {
        Long id = 1L;
        DishRequestUpdateDto updateDto = mock(DishRequestUpdateDto.class);
        DishUpdateModel updateModel = mock(DishUpdateModel.class);
        DishModel dishModel = mock(DishModel.class);
        DishResponseDto responseDto = mock(DishResponseDto.class);

        when(dishMapper.toDishUpdateModel(updateDto)).thenReturn(updateModel);
        when(dishServicePort.updateDish(id, updateModel)).thenReturn(dishModel);
        when(dishMapper.toDishResponseDto(dishModel)).thenReturn(responseDto);

        DishResponseDto result = dishHandler.updateDish(id, updateDto);

        verify(dishMapper).toDishUpdateModel(updateDto);
        verify(dishServicePort).updateDish(id, updateModel);
        verify(dishMapper).toDishResponseDto(dishModel);
        assertEquals(responseDto, result);
    }}