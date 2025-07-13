package com.foodcourt.traceabilitymanagement.application.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponseDto {

    private String name;
    private String lastName;
    private String dni;
    private String phoneNumber;
    private Date birthdayDate;
    private Long idRol;
    private Long id;
    private Long employeeRestaurantId;
}
