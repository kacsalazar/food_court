package com.foodcourt.squaremallmanagment.application.handler.impl;

import com.foodcourt.squaremallmanagment.application.dto.request.DishCreateRequest;
import com.foodcourt.squaremallmanagment.application.dto.request.DishRequestUpdateDto;
import com.foodcourt.squaremallmanagment.application.dto.response.DishResponseDto;
import com.foodcourt.squaremallmanagment.application.handler.IDishHandler;
import com.foodcourt.squaremallmanagment.application.handler.util.UtilClass;
import com.foodcourt.squaremallmanagment.application.mapper.IDishRequestMapper;
import com.foodcourt.squaremallmanagment.application.mapper.impl.DishRequestMapperModel;
import com.foodcourt.squaremallmanagment.domain.api.IDishServicePort;
import com.foodcourt.squaremallmanagment.domain.model.DishModel;
import com.foodcourt.squaremallmanagment.domain.model.DishUpdateModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
@Transactional
public class DishHandler implements IDishHandler {

    private final IDishRequestMapper dishMapper;
    private final IDishServicePort dishServicePort;

    @Override
    public void saveDish(DishCreateRequest dishCreateRequest) {
        DishModel dishModel = DishRequestMapperModel.toDishModel(dishCreateRequest);
        dishServicePort.saveDish(dishModel);
    }

    @Override
    public DishResponseDto updateDish(Long id, DishRequestUpdateDto dishRequestUpdateDto) {
        DishUpdateModel dishUpdateModel = dishMapper.toDishUpdateModel(dishRequestUpdateDto);
        return DishRequestMapperModel.toDishResponseDto(dishServicePort.updateDish(id, dishUpdateModel));
    }

    @Override
    public DishResponseDto disableDish(Long id, Boolean status) {
        String dniOwner = UtilClass.getUserDni();
        return DishRequestMapperModel.toDishResponseDto(dishServicePort.disableDish(id, status, dniOwner));
    }
}
