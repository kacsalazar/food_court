package com.foodcourt.usersmanagment;

import com.foodcourt.usersmanagment.application.dto.request.OwnerRequestDto;
import com.foodcourt.usersmanagment.application.dto.request.UserRequestDto;
import com.foodcourt.usersmanagment.application.dto.response.UserResponseDto;
import com.foodcourt.usersmanagment.domain.model.*;
import com.foodcourt.usersmanagment.infrastructure.out.jpa.entity.RolEntity;
import com.foodcourt.usersmanagment.infrastructure.out.jpa.entity.UserEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class CreatorMocks {

    public static CreateUserModel createOwnerModel() {

        LocalDate localDate = LocalDate.parse("1998-08-12");
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());


        return CreateUserModel.builder()
                .idRol(2L)
                .name("John Doe")
                .lastName("Martinez")
                .dni("123")
                .phoneNumber("123456789")
                .password("securePassword")
                .email("jdoe@mail.com")
                .build();
    }


    public static UserRequestDto createOwnerRequestDto() {
        return UserRequestDto.builder()
                .name("John Doe")
                .lastName("Martinez")
                .dni("123")
                .phoneNumber("123456789")
                .password("securePassword")
                .email("")
                .build();
    }

    public static OwnerRequestDto createEmployeeRequestDto() {
        return OwnerRequestDto.builder()
                .name("John Doe")
                .lastName("Martinez")
                .dni("123")
                .phoneNumber("123456789")
                .password("securePassword")
                .email("")
               .build();
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
                .email("jdoe@email.com")
               .build();
    }

    public static UserModel createUserModel() {
        return UserModel.builder()
                .idRol(1L)
                .name("John Doe")
                .lastName("Martinez")
                .dni("123")
                .email("jdoe@email.com")
                .phoneNumber("123456789")
                .password("securePassword")
                .build();
    }

    public static UserResponseDto createUserResponseDto() {
        return UserResponseDto.builder()
                .name("John Doe")
                .lastName("Martinez")
                .dni("123")
                .phoneNumber("123456789")
               .build();
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

    public static TokenModel createTokenModel() {
        return TokenModel.builder().token("jwtToken").build();
    }



    public static AuthModel createAuthModel() {
        return AuthModel.builder()
                .email("jdoe@email.com")
                .password("securePassword")
                .build();
    }

    public static CreateUserModel createCreateUserModel(){
        return CreateUserModel.builder()
                .dni("123456789")
                .name("John Doe")
                .email("john@example.com")
                .password("securePassword")
                .phoneNumber("3136871174")
                .idRol(1L)
                .build();

    }

    public static RestaurantModel createRestaurantModel() {
        return RestaurantModel.builder()
                .name("Food Court")
                .ownerDni("234")
                .address("123 Main St")
                .phoneNumber("987654321")
                .id(1L)
                .build();
    }

    public static CreateUserModel createSaveUserModel() throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate = sdf.parse("1998-06-25");
        return CreateUserModel.builder()
                .idRol(1L)
                .name("John Doe")
                .lastName("Martinez")
                .dni("123")
                .email("jdoe@email.com")
                .phoneNumber("123456789")
                .password("securePassword")
                .birthdayDate(parsedDate)
                .build();
    }

    public static UserModel createEmployeeModel() {
        return UserModel.builder()
                .id(1L)
                .idRol(2L)
                .name("John Doe")
                .lastName("Martinez")
                .dni("234")
                .email("jdoe@email.com")
                .phoneNumber("123456789")
                .password("securePassword")
                .build();
    }

    public static CreateUserModel createCustomerModel() throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate = sdf.parse("1998-06-25");
        return CreateUserModel.builder()
                .idRol(4L)
                .name("John Doe")
                .lastName("Martinez")
                .dni("234")
                .email("jdoe@email.com")
                .phoneNumber("123456789")
                .password("securePassword")
                .birthdayDate(parsedDate)
                .build();
    }

    public static CreateUserModel createCreateEmployeeModel() throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate = sdf.parse("1998-06-25");
        return CreateUserModel.builder()
                .idRol(2L)
                .name("John Doe")
                .lastName("Martinez")
                .dni("234")
                .email("jdoe@email.com")
                .phoneNumber("123456789")
                .password("securePassword")
                .birthdayDate(parsedDate)
                .idRestaurant(1L)
                .build();
    }

    public static UserModel createCreateOwnerModel() {
        return UserModel.builder()
                .id(1L)
                .idRol(2L)
                .name("John Doe")
                .lastName("Martinez")
                .dni("234")
                .email("jdoe@email.com")
                .phoneNumber("123456789")
                .password("securePassword")
                .idRestaurant(1L)
                .build();
    }
}
