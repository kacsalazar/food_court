package com.foodcourt.usersmanagment.infrastructure.out.jpa.mapper;

import com.foodcourt.usersmanagment.domain.model.ObjectModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IObjectEntityMapper {

    /*ObjectEntity toEntity(ObjectModel user);
    ObjectModel toObjectModel(ObjectEntity objectEntity);
    List<ObjectModel> toObjectModelList(List<ObjectEntity> userEntityList);
    */
}