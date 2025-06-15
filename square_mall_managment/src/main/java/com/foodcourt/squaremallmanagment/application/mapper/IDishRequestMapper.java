package com.foodcourt.squaremallmanagment.application.mapper;

import com.foodcourt.squaremallmanagment.application.dto.request.DishCreateRequest;
import com.foodcourt.squaremallmanagment.application.dto.request.DishRequestUpdateDto;
import com.foodcourt.squaremallmanagment.application.dto.response.DishResponse;
import com.foodcourt.squaremallmanagment.application.dto.response.DishRestaurantResponse;
import com.foodcourt.squaremallmanagment.domain.model.DishModel;
import com.foodcourt.squaremallmanagment.domain.model.DishUpdateModel;
import com.foodcourt.squaremallmanagment.domain.model.ListDishesByRestaurantModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IDishRequestMapper {

    DishModel toDishModel(DishCreateRequest dishCreateRequest);
    DishUpdateModel toDishUpdateModel(DishRequestUpdateDto dishRequestUpdateDto);
    DishResponse toDishResponseDto(DishModel dishModel);
    List<DishRestaurantResponse> toListDishResponseDto(List<ListDishesByRestaurantModel> dishModels);
}
