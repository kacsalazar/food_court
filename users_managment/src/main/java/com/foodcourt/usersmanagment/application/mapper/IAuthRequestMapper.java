package com.foodcourt.usersmanagment.application.mapper;

import com.foodcourt.usersmanagment.application.dto.request.AuthRequestDto;
import com.foodcourt.usersmanagment.domain.model.AuthModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IAuthRequestMapper {

    AuthModel toAuthModel(AuthRequestDto authRequestDto);
}
