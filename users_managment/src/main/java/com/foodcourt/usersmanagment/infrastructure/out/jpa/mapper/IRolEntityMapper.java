package com.foodcourt.usersmanagment.infrastructure.out.jpa.mapper;

import com.foodcourt.usersmanagment.domain.model.GetRolModel;
import com.foodcourt.usersmanagment.infrastructure.out.jpa.entity.RolEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IRolEntityMapper {

    GetRolModel toGetRolModel(RolEntity rolEntity);

}
