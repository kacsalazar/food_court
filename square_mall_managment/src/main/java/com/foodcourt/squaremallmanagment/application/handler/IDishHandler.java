package com.foodcourt.squaremallmanagment.application.handler;

import com.foodcourt.squaremallmanagment.application.dto.request.DishRequestDto;
import com.foodcourt.squaremallmanagment.application.dto.request.DishRequestUpdateDto;
import com.foodcourt.squaremallmanagment.application.dto.response.DishResponseDto;

public interface IDishHandler {

    void saveDish(DishRequestDto dishRequestDto);
    DishResponseDto updateDish(Long id, DishRequestUpdateDto dishRequestUpdateDto);
}
