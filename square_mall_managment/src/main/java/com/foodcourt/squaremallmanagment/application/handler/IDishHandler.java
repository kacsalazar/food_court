package com.foodcourt.squaremallmanagment.application.handler;

import com.foodcourt.squaremallmanagment.application.dto.request.DishRequestDto;

public interface IDishHandler {

    void saveDish(DishRequestDto dishRequestDto);
}
