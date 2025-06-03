package com.foodcourt.usersmanagment;

import com.foodcourt.usersmanagment.application.dto.request.OwnerRequestDto;
import com.foodcourt.usersmanagment.application.dto.response.UserResponseDto;
import com.foodcourt.usersmanagment.domain.model.OwnerModel;
import com.foodcourt.usersmanagment.domain.model.RolModel;
import com.foodcourt.usersmanagment.domain.model.UserModel;
import com.foodcourt.usersmanagment.infrastructure.out.jpa.entity.RolEntity;
import com.foodcourt.usersmanagment.infrastructure.out.jpa.entity.UserEntity;

import java.util.Date;

public class CreatorMocks {

    public static OwnerModel createOwnerModel() {
        return OwnerModel.builder()
                .name("John Doe")
                .lastName("Martinez")
                .dni("123")
                .phoneNumber("123456789")
                .password("securePassword")
                .email("")
                .birthdayDate(new Date()).build();
    }


    public static OwnerRequestDto createOwnerRequestDto() {
        return OwnerRequestDto.builder()
                .name("John Doe")
                .lastName("Martinez")
                .dni("123")
                .phoneNumber("123456789")
                .password("securePassword")
                .email("")
                .birthdayDate(new Date()).build();
    }

    public static UserEntity createUserEntity() {
        return UserEntity.builder()
                .id(1L)
                .idRol(2L)
                .name("John Doe")
                .lastName("Martinez")
                .dni("123")
                .phoneNumber("123456789")
                .password("securePassword")
                .email("")
                .birthdayDate(new Date()).build();
    }

    public static UserModel createUserModel() {
        return UserModel.builder()
                .idRol(2L)
                .name("John Doe")
                .lastName("Martinez")
                .dni("123")
                .phoneNumber("123456789")
                .birthdayDate(new Date()).build();
    }

    public static UserResponseDto createUserResponseDto() {
        return UserResponseDto.builder()
                .name("John Doe")
                .lastName("Martinez")
                .dni("123")
                .phoneNumber("123456789")
                .birthdayDate(new Date()).build();
    }

    public static RolEntity createRolEntity() {
        return RolEntity.builder()
                .id(1L)
                .name("ROLE_ADMIN")
                .description("Administrator Role").build();
    }

    public static RolModel createRolModel() {
        return RolModel.builder()
                .id(1L)
                .name("ROLE_ADMIN")
                .description("Administrator Role").build();
    }
}
