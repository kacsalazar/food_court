package com.foodcourt.squaremallmanagment.application.handler.impl;

import com.foodcourt.squaremallmanagment.application.dto.request.RestaurantRequestDto;
import com.foodcourt.squaremallmanagment.application.dto.response.GetRestaurantByOwnerResponse;
import com.foodcourt.squaremallmanagment.application.dto.response.RestaurantByIdResponse;
import com.foodcourt.squaremallmanagment.application.dto.response.RestaurantResponse;
import com.foodcourt.squaremallmanagment.application.handler.IRestaurantHandler;
import com.foodcourt.squaremallmanagment.application.mapper.IRestaurantRequestMapper;
import com.foodcourt.squaremallmanagment.domain.api.IRestaurantServicePort;
import com.foodcourt.squaremallmanagment.domain.model.restaurant.RestaurantModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
@Transactional
public class RestaurantHandler implements IRestaurantHandler {

    private final IRestaurantRequestMapper restaurantMapper;
    private final IRestaurantServicePort restaurantServicePort;


    @Override
    public void saveRestaurant(RestaurantRequestDto restaurantRequestDto) {

        RestaurantModel restaurantModel = restaurantMapper.toRestaurantModel(restaurantRequestDto);
        restaurantServicePort.saveRestaurant(restaurantModel);
    }

    @Override
    public List<RestaurantResponse> getAllRestaurants(Integer page, Integer size) {
        Integer offset = (page - 1) * size;
        return restaurantMapper.toRestaurantResponseList(
                restaurantServicePort.getAllRestaurants(offset, size));
    }

    @Override
    public GetRestaurantByOwnerResponse getRestaurantByIdOwner(Long idOwner) {
        return restaurantMapper.toGetRestaurantByOwnerResponse(
                restaurantServicePort.getRestaurantByIdOwner(idOwner));
    }

    @Override
    public RestaurantByIdResponse findRestaurantById(Long restaurantId) {
        return restaurantMapper.toRestaurantResponse(restaurantServicePort.findRestaurantById(restaurantId));
    }
}
