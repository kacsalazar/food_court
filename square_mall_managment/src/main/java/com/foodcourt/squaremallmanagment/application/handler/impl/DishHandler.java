package com.foodcourt.squaremallmanagment.application.handler.impl;

import com.foodcourt.squaremallmanagment.application.dto.request.DishCreateRequest;
import com.foodcourt.squaremallmanagment.application.dto.request.DishRequestUpdateDto;
import com.foodcourt.squaremallmanagment.application.dto.response.DishResponse;
import com.foodcourt.squaremallmanagment.application.dto.response.DishRestaurantResponse;
import com.foodcourt.squaremallmanagment.application.handler.IDishHandler;
import com.foodcourt.squaremallmanagment.application.handler.helper.HelperClass;
import com.foodcourt.squaremallmanagment.application.mapper.IDishRequestMapper;
import com.foodcourt.squaremallmanagment.application.mapper.impl.DishRequestMapperModel;
import com.foodcourt.squaremallmanagment.domain.api.IDishServicePort;
import com.foodcourt.squaremallmanagment.domain.model.dish.DishModel;
import com.foodcourt.squaremallmanagment.domain.model.dish.DishUpdateModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
@Transactional
public class DishHandler implements IDishHandler {

    private final IDishRequestMapper dishMapper;
    private final IDishServicePort dishServicePort;
    private final HelperClass helperClass;

    @Override
    public void saveDish(DishCreateRequest dishCreateRequest) {
        DishModel dishModel = DishRequestMapperModel.toDishModel(dishCreateRequest);
        dishServicePort.saveDish(dishModel, helperClass.getUserDni());
    }

    @Override
    public DishResponse updateDish(Long dishId, DishRequestUpdateDto dishRequestUpdateDto) {
        DishUpdateModel dishUpdateModel = dishMapper.toDishUpdateModel(dishRequestUpdateDto);
        return DishRequestMapperModel.toDishResponseDto(dishServicePort.updateDish(dishId, dishUpdateModel,helperClass.getUserDni()));
    }

    @Override
    public DishResponse disableDish(Long dishId, Boolean status) {
        return DishRequestMapperModel.toDishResponseDto(dishServicePort.disableDish(dishId, status, helperClass.getUserDni()));
    }

    @Override
    public List<DishRestaurantResponse> getDishesByCategory(Long idRestaurant, Long idCategory, Integer page, Integer size) {
        Integer offset = (page - 1) * size;

        return dishMapper.toListDishResponseDto(dishServicePort.getDishesByCategory(
                DishRequestMapperModel.toListDishesRetrieve(idRestaurant, idCategory, offset, size)));

    }
}
