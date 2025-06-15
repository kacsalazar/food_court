package com.foodcourt.usersmanagment.application.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TokenResponseDto {
    private String token;
}
