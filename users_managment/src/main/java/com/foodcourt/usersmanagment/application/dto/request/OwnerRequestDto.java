package com.foodcourt.usersmanagment.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OwnerRequestDto {

    private String name;
    private String lastName;
    private String dni;
    private String phoneNumber;
    private String password;
    private String email;
    private Date birthdayDate;
}
