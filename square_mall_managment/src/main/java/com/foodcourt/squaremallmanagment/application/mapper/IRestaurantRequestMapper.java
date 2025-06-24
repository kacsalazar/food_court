package com.foodcourt.squaremallmanagment.application.mapper;

import com.foodcourt.squaremallmanagment.application.dto.request.RestaurantRequestDto;
import com.foodcourt.squaremallmanagment.application.dto.response.GetRestaurantByOwnerResponse;
import com.foodcourt.squaremallmanagment.application.dto.response.RestaurantResponse;
import com.foodcourt.squaremallmanagment.domain.model.restaurant.RestaurantModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestaurantRequestMapper {

    // Cambiar nombre a toModel.
    RestaurantModel toRestaurantModel(RestaurantRequestDto restaurantRequestDto);
    List<RestaurantResponse> toRestaurantResponseList (List<RestaurantModel> restaurantModels);
    GetRestaurantByOwnerResponse toGetRestaurantByOwnerResponse(RestaurantModel restaurantModel);
}
