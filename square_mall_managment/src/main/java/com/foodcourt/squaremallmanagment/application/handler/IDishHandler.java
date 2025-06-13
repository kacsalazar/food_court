package com.foodcourt.squaremallmanagment.application.handler;

import com.foodcourt.squaremallmanagment.application.dto.request.DishCreateRequest;
import com.foodcourt.squaremallmanagment.application.dto.request.DishRequestUpdateDto;
import com.foodcourt.squaremallmanagment.application.dto.response.DishResponseDto;

public interface IDishHandler {

    void saveDish(DishCreateRequest dishCreateRequest);
    DishResponseDto updateDish(Long id, DishRequestUpdateDto dishRequestUpdateDto);
    DishResponseDto disableDish(Long id, Boolean status);
}
