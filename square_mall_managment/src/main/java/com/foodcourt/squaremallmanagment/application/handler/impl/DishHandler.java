package com.foodcourt.squaremallmanagment.application.handler.impl;

import com.foodcourt.squaremallmanagment.application.dto.request.DishRequestDto;
import com.foodcourt.squaremallmanagment.application.dto.request.DishRequestUpdateDto;
import com.foodcourt.squaremallmanagment.application.dto.response.DishResponseDto;
import com.foodcourt.squaremallmanagment.application.handler.IDishHandler;
import com.foodcourt.squaremallmanagment.application.mapper.IDishRequestMapper;
import com.foodcourt.squaremallmanagment.domain.api.IDishServicePort;
import com.foodcourt.squaremallmanagment.domain.model.DishModel;
import com.foodcourt.squaremallmanagment.domain.model.DishUpdateModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
public class DishHandler implements IDishHandler {

    private final IDishRequestMapper dishMapper;
    private final IDishServicePort dishServicePort;

    @Override
    public void saveDish(DishRequestDto dishRequestDto) {

        DishModel dishModel = dishMapper.toDishModel(dishRequestDto);
        dishServicePort.saveDish(dishModel);
    }

    @Override
    public DishResponseDto updateDish(Long id, DishRequestUpdateDto dishRequestUpdateDto) {
        DishUpdateModel dishUpdateModel = dishMapper.toDishUpdateModel(dishRequestUpdateDto);
        return dishMapper.toDishResponseDto(dishServicePort.updateDish(id, dishUpdateModel));
    }
}
