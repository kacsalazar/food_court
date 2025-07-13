package com.foodcourt.traceabilitymanagement.application.handler;

import com.foodcourt.traceabilitymanagement.domain.model.ClaimUserModel;

public interface ITokenValidator {

    Boolean isValidToken(String token);
    String generateToken(ClaimUserModel claimUserModel);
}
