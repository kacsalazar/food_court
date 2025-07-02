package com.foodcourt.squaremallmanagment.application.handler.impl;

import com.foodcourt.squaremallmanagment.application.dto.request.DishCreateRequest;
import com.foodcourt.squaremallmanagment.application.dto.request.DishRequestUpdateDto;
import com.foodcourt.squaremallmanagment.application.dto.response.DishResponse;
import com.foodcourt.squaremallmanagment.application.handler.helper.HelperClass;
import com.foodcourt.squaremallmanagment.application.mapper.IDishRequestMapper;
import com.foodcourt.squaremallmanagment.domain.api.IDishServicePort;
import com.foodcourt.squaremallmanagment.domain.model.dish.DishModel;
import com.foodcourt.squaremallmanagment.domain.model.dish.DishUpdateModel;
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
        // Arrange
        DishCreateRequest request = new DishCreateRequest();
        request.setName("Pizza Margarita");
        request.setDescription("Pizza clásica con tomate y albahaca");
        request.setPrice(12.5);
        request.setImageUrl("https://dummyimage.com/pizza-margarita.jpg");
        request.setIdCategory(2L);
        request.setIdRestaurant(5L);
        // Llénalo si es necesario
        DishModel model = DishModel.builder()
            .dishInfo(DishModel.DishInfo.builder()
                .name("Pizza Margarita")
                .description("Pizza clásica con tomate y albahaca")
                .price(12.5)
                .imageUrl("https://dummyimage.com/pizza-margarita.jpg")
                .idCategory(2L)
                .isActive(true)
                .build())
            .restaurantInfo(DishModel.RestaurantInfo.builder()
                .idRestaurant(5L)
                .build())
            .build();

        mockStatic(HelperClass.class);
        when(HelperClass.getUserDni()).thenReturn("123");
        // Act
        dishHandler.saveDish(request);

        // Assert
        verify(dishServicePort).saveDish(model, "123"); // Asumiendo que "123" es el DNI del usuario
    }

    @Test
    void updateDish() {
        Long id = 1L;
        DishRequestUpdateDto updateDto = mock(DishRequestUpdateDto.class);
        DishUpdateModel updateModel = mock(DishUpdateModel.class);
        DishModel dishModel = mock(DishModel.class);
        DishResponse responseDto = mock(DishResponse.class);

        when(dishMapper.toDishUpdateModel(updateDto)).thenReturn(updateModel);
        when(dishServicePort.updateDish(id, updateModel, "123")).thenReturn(dishModel);
        when(dishMapper.toDishResponseDto(dishModel)).thenReturn(responseDto);

        DishResponse result = dishHandler.updateDish(id, updateDto);

        verify(dishMapper).toDishUpdateModel(updateDto);
        verify(dishServicePort).updateDish(id, updateModel, "123");
        verify(dishMapper).toDishResponseDto(dishModel);
        assertEquals(responseDto, result);
    }
}