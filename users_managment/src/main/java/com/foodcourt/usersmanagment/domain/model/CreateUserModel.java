package com.foodcourt.usersmanagment.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CreateUserModel {

    private String name;
    private String lastName;
    private String dni;
    private String phoneNumber;
    private String password;
    private String email;
    private Date birthdayDate;
    private Long idRol;
    private Long idRestaurant;
}
