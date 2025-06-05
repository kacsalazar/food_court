package com.foodcourt.usersmanagment.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenModel {

    private String token;
}
