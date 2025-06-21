package com.foodcourt.usersmanagment.application.mapper;

import com.foodcourt.usersmanagment.application.dto.request.UserRequestDto;
import com.foodcourt.usersmanagment.application.dto.response.UserResponseDto;
import com.foodcourt.usersmanagment.domain.model.CreateUserModel;
import com.foodcourt.usersmanagment.domain.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserRequestMapper {

    CreateUserModel toUserToSave(UserRequestDto userRequestDto);
    UserResponseDto toUserResponseDto(UserModel userModel);
}
