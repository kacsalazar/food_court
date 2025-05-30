package com.foodcourt.usersmanagment.application.mapper;

import com.foodcourt.usersmanagment.application.dto.request.ObjectRequestDto;
import com.foodcourt.usersmanagment.domain.model.ObjectModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IObjectRequestMapper {
    ObjectModel toObject(ObjectRequestDto objectRequestDto);
}
