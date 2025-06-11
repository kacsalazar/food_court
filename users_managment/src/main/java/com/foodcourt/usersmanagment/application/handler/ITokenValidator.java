package com.foodcourt.usersmanagment.application.handler;

import io.jsonwebtoken.Claims;

public interface ITokenValidator {

    Boolean isValidToken(String token);
}
