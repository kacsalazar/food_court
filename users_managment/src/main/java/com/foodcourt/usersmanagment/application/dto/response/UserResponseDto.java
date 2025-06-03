package com.foodcourt.usersmanagment.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserResponseDto {

    private String name;
    private String lastName;
    private String dni;
    private String phoneNumber;
    private Date birthdayDate;
    private String idRol;
}
