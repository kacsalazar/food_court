package com.foodcourt.usersmanagment.domain.usecase;

import com.foodcourt.usersmanagment.domain.api.IAuthServicePort;
import com.foodcourt.usersmanagment.domain.model.AuthModel;
import com.foodcourt.usersmanagment.domain.model.ClaimUserModel;
import com.foodcourt.usersmanagment.domain.model.TokenModel;
import com.foodcourt.usersmanagment.domain.spi.IAuthPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AuthUseCase implements IAuthServicePort {

    private final IAuthPort authPort;

    @Override
    public TokenModel userLogin(AuthModel authModel) {

       return authPort.userLogin(authModel);
    }
}
