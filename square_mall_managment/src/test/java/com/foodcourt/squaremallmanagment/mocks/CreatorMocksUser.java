package com.foodcourt.squaremallmanagment.mocks;

import com.foodcourt.squaremallmanagment.domain.model.UserModel;

public class CreatorMocksUser {

    public static UserModel createUserModel() {
        return UserModel.builder()
                .id(1L)
                .dni("123")
                .name("John Doe")
                .email("jdoe@mail.com")
                .phoneNumber("3136").build();
    }

    public static UserModel buildEmployeeModel() {
        return UserModel.builder()
                .id(2L)
                .dni("456")
                .email("employee@mail.com")
                .name("Employee Name")
                .build();
    }

    public static UserModel buildUserModel() {
        return UserModel.builder()
                .id(10L)
                .dni("123")
                .email("user@mail.com")
                .name("User Name")
                .phoneNumber("3136")
                .build();
    }
}
