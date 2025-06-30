package com.foodcourt.usersmanagment.application.mapper.impl;

import com.foodcourt.usersmanagment.application.dto.response.UserResponseDto;
import com.foodcourt.usersmanagment.domain.model.UserModel;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserRequestMapper {

    public static UserResponseDto toUserResponseDto(UserModel user){
        return UserResponseDto.builder()
                .name(user.getName())
                .lastName(user.getLastName())
                .dni(user.getDni())
                .phoneNumber(user.getPhoneNumber())
                .birthdayDate(user.getBirthdayDate())
                .idRol(user.getIdRol())
                .id(user.getId())
                .employeeRestaurantId(user.getIdRestaurant())
                .build();
    }
}
