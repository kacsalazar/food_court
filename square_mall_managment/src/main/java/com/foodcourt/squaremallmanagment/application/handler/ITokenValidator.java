package com.foodcourt.squaremallmanagment.application.handler;

import io.jsonwebtoken.Claims;

public interface ITokenValidator {

    Boolean isValidToken(String token);
}
