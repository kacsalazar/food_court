package com.foodcourt.usersmanagment.application.handler;

import io.jsonwebtoken.Claims;

public interface ITokenValidator {

    Claims validateToken(String token);
}
