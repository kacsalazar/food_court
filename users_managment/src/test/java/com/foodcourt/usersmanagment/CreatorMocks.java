package com.foodcourt.usersmanagment;

import com.foodcourt.usersmanagment.application.dto.request.UserRequestDto;
import com.foodcourt.usersmanagment.application.dto.response.UserResponseDto;
import com.foodcourt.usersmanagment.domain.model.CreateUserModel;
import com.foodcourt.usersmanagment.domain.model.RolModel;
import com.foodcourt.usersmanagment.domain.model.UserModel;
import com.foodcourt.usersmanagment.infrastructure.out.jpa.entity.RolEntity;
import com.foodcourt.usersmanagment.infrastructure.out.jpa.entity.UserEntity;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class CreatorMocks {

    public static CreateUserModel createOwnerModel() {

        LocalDate localDate = LocalDate.parse("1998-08-12");
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());


        return CreateUserModel.builder()
                .name("John Doe")
                .lastName("Martinez")
                .dni("123")
                .phoneNumber("123456789")
                .password("securePassword")
                .email("mdoe@mail.com")
                .birthdayDate(date).build();
    }


    public static UserRequestDto createOwnerRequestDto() {
        return UserRequestDto.builder()
                .name("John Doe")
                .lastName("Martinez")
                .dni("123")
                .phoneNumber("123456789")
                .password("securePassword")
                .email("")
                .birthdayDate(new Date()).build();
    }

    public static UserRequestDto createEmployeeRequestDto() {
        return UserRequestDto.builder()
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
