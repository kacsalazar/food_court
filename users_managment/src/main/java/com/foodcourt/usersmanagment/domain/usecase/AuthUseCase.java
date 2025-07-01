package com.foodcourt.usersmanagment.domain.usecase;

import com.foodcourt.usersmanagment.domain.api.IAuthServicePort;
import com.foodcourt.usersmanagment.domain.model.AuthModel;
import com.foodcourt.usersmanagment.domain.model.TokenModel;
import com.foodcourt.usersmanagment.domain.model.UserModel;
import com.foodcourt.usersmanagment.domain.spi.IAuthPort;
import com.foodcourt.usersmanagment.domain.spi.IRolPersistencePort;
import com.foodcourt.usersmanagment.domain.spi.IUserPersistencePort;
import com.foodcourt.usersmanagment.domain.exception.InvalidPasswordException;
import com.foodcourt.usersmanagment.domain.exception.RolNotFoundException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AuthUseCase implements IAuthServicePort {

    private final IAuthPort authPort;
    private final IRolPersistencePort rolPort;
    private final IUserPersistencePort userPersistencePort;

    @Override
    public TokenModel userLogin(AuthModel authModel) {
        UserModel user = userPersistencePort.findUserByEmail(authModel.getEmail());

        String rolName = rolPort.findById(user.getIdRol()).getName();
        if (rolName == null)
            throw new RolNotFoundException();

        if (!authPort.verifyPassword(authModel.getPassword(), user.getPassword()))
            throw new InvalidPasswordException();

       return authPort.userLogin(authModel, user, rolName);
    }
}
