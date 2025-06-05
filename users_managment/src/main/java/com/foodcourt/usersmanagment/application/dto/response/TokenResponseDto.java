package com.foodcourt.usersmanagment.application.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
public class TokenResponseDto {
    private String token;
}
