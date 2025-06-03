package com.foodcourt.usersmanagment;

import com.foodcourt.usersmanagment.application.dto.request.OwnerRequestDto;
import com.foodcourt.usersmanagment.domain.model.OwnerModel;
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
}
