package com.foodcourt.squaremallmanagment.application.handler.impl;

import com.foodcourt.squaremallmanagment.application.dto.request.RestaurantRequestDto;
import com.foodcourt.squaremallmanagment.application.handler.IRestaurantHandler;
import com.foodcourt.squaremallmanagment.application.mapper.IRestaurantRequestMapper;
import com.foodcourt.squaremallmanagment.domain.api.IRestaurantServicePort;
import com.foodcourt.squaremallmanagment.domain.api.IUserClientServicePort;
import com.foodcourt.squaremallmanagment.domain.model.RestaurantModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
@Transactional
public class RestaurantHandler implements IRestaurantHandler {

    private final IRestaurantRequestMapper restaurantMapper;
    private final IRestaurantServicePort restaurantServicePort;
    private final IUserClientServicePort userClientServicePort;


    @Override
    public void saveRestaurant(RestaurantRequestDto restaurantRequestDto) {
        if (!userClientServicePort.isValidUser(
                restaurantRequestDto.getIdOwner(), "ROLE_OWNER")) {
            log.error("Invalid user role for restaurant creation");
            throw new RuntimeException("Invalid user role for restaurant creation");
        }

        RestaurantModel restaurantModel = restaurantMapper.toRestaurant(restaurantRequestDto);
        restaurantServicePort.saveRestaurant(restaurantModel);
    }
}
