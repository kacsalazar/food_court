package com.foodcourt.squaremallmanagment.infrastructure.out.jpa.mapper;

import com.foodcourt.squaremallmanagment.domain.model.DishModel;
import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.entity.DishEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IDishEntityMapper {

    DishEntity toDishEntity(DishModel dishModel);
    DishModel toDishModel(DishEntity dishEntity);
    List<DishModel> toDishesModel(List<DishEntity> dishEntities);

}
