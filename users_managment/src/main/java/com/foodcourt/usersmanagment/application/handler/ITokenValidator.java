package com.foodcourt.usersmanagment.application.handler;

import com.foodcourt.usersmanagment.domain.model.ClaimUserModel;
import io.jsonwebtoken.Claims;

public interface ITokenValidator {

    Boolean isValidToken(String token);
    String generateToken(ClaimUserModel claimUserModel);
}
