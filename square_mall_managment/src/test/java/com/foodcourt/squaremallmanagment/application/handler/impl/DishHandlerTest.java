package com.foodcourt.squaremallmanagment.application.handler.impl;

import com.foodcourt.squaremallmanagment.application.dto.request.DishCreateRequest;
import com.foodcourt.squaremallmanagment.application.dto.request.DishRequestUpdateDto;
import com.foodcourt.squaremallmanagment.application.dto.response.DishResponse;
import com.foodcourt.squaremallmanagment.application.dto.response.DishRestaurantResponse;
import com.foodcourt.squaremallmanagment.application.handler.helper.HelperClass;
import com.foodcourt.squaremallmanagment.application.mapper.IDishRequestMapper;
import com.foodcourt.squaremallmanagment.domain.api.IDishServicePort;
import com.foodcourt.squaremallmanagment.domain.model.dish.DishModel;
import com.foodcourt.squaremallmanagment.domain.model.dish.DishUpdateModel;
import com.foodcourt.squaremallmanagment.domain.model.dish.ListDishesByRestaurantModel;
import com.foodcourt.squaremallmanagment.mocks.CreatorDishMocks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DishHandlerTest {

    @Mock
    private IDishRequestMapper dishMapper;

    @Mock
    private IDishServicePort dishServicePort;

    @InjectMocks
    private DishHandler dishHandler;

    @Mock
    private HelperClass helperClass;

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
                .build())
            .restaurantInfo(DishModel.RestaurantInfo.builder()
                .idRestaurant(5L)
                .build())
            .build();

        when(helperClass.getUserDni()).thenReturn("123");
        // Act
        dishHandler.saveDish(request);

        // Assert
        verify(dishServicePort).saveDish(model, "123"); // Asumiendo que "123" es el DNI del usuario
    }

    @Test
    void updateDish() {
        Long id = 1L;
        DishRequestUpdateDto requestDto = CreatorDishMocks.createDishRequestDto();
        DishUpdateModel updateModel = CreatorDishMocks.createDishUpdateModel();
        DishModel updatedDish = CreatorDishMocks.createDishModel();
        DishResponse expectedResponse = CreatorDishMocks.createDishResponse();

        when(helperClass.getUserDni()).thenReturn("1234");
        when(dishMapper.toDishUpdateModel(requestDto)).thenReturn(updateModel);
        when(dishServicePort.updateDish(id, updateModel, "1234")).thenReturn(updatedDish);

        DishResponse result = dishHandler.updateDish(id, requestDto);

        assertEquals(expectedResponse, result);
    }

    @Test
    void disableDish() {
        Long id = 1L;
        Boolean status = false;
        DishModel updatedDish = CreatorDishMocks.createDishModel();
        DishResponse expectedResponse =CreatorDishMocks.createDishResponse();

        when(helperClass.getUserDni()).thenReturn("1234");
        when(dishServicePort.disableDish(id, status, "1234")).thenReturn(updatedDish);

        DishResponse result = dishHandler.disableDish(id, status);

        assertEquals(expectedResponse, result);
    }

    @Test
    void getDishesByCategory() {
        Long restaurantId = 1L;
        Long categoryId = 2L;
        Integer page = 0;
        Integer size = 5;

        List<ListDishesByRestaurantModel> dishes = List.of(new ListDishesByRestaurantModel());
        List<DishRestaurantResponse> expectedList = List.of(new DishRestaurantResponse());

        when(dishServicePort.getDishesByCategory(restaurantId, categoryId, page, size)).thenReturn(dishes);
        when(dishMapper.toListDishResponseDto(dishes)).thenReturn(expectedList);

        List<DishRestaurantResponse> result =
                dishHandler.getDishesByCategory(restaurantId, categoryId, page, size);

        assertEquals(expectedList, result);
    }
}