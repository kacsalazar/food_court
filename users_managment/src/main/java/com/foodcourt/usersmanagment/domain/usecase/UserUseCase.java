package com.foodcourt.usersmanagment.domain.usecase;

import com.foodcourt.usersmanagment.domain.api.IUserServicePort;
import com.foodcourt.usersmanagment.domain.exception.ConstantException;
import com.foodcourt.usersmanagment.domain.exception.DomainException;
import com.foodcourt.usersmanagment.domain.model.CreateUserModel;
import com.foodcourt.usersmanagment.domain.model.RolModel;
import com.foodcourt.usersmanagment.domain.model.UserModel;
import com.foodcourt.usersmanagment.domain.spi.IRestaurantClientPort;
import com.foodcourt.usersmanagment.domain.spi.IRolPersistencePort;
import com.foodcourt.usersmanagment.domain.spi.IUserPersistencePort;
import com.foodcourt.usersmanagment.domain.usecase.util.UseValidationUtil;
import com.foodcourt.usersmanagment.domain.exception.UserNotAuthorizedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;
    private final IRolPersistencePort rolPersistencePort;
    private final IRestaurantClientPort restaurantClientPort;


    @Override
    public void saveOwner(CreateUserModel createUserModel) {
        UseValidationUtil.isValidUser(createUserModel);
        Long idRol = rolPersistencePort.findByName("ROLE_OWNER").getId();
        if(idRol == null)
            throw new DomainException(ConstantException.ROLE_NOT_FOUND);

        createUserModel.setIdRol(idRol);
        userPersistencePort.saveUser(createUserModel);
    }

    @Override
    public UserModel findUserById(Long id) {
        return Optional.ofNullable(userPersistencePort.findUserById(id))
                .orElseThrow(() -> new DomainException(ConstantException.USER_NOT_FOUND));
    }

    @Override
    public Boolean verifyUserRol(String dni, String role) {
        UserModel user = userPersistencePort.findUserByDni(dni);
        if (user == null)
            throw new DomainException(ConstantException.USER_NOT_FOUND);

        return Optional.ofNullable(verifyUserRol(user, role))
                .orElseThrow(() -> new DomainException(ConstantException.INVALID_USER));
    }

    @Override
    public void createAccountEmployee(CreateUserModel createUserModel, String ownerDni) {

        UseValidationUtil.isValidUser(createUserModel);
        Long idRol = rolPersistencePort.findByName("ROLE_EMPLOYEE").getId();
        if(idRol == null)
            throw new DomainException(ConstantException.ROLE_NOT_FOUND);

        validateUserRestaurant(createUserModel.getIdRestaurant(),
                restaurantClientPort.getRestaurantIdByOwner(
                        userPersistencePort.findUserByDni(ownerDni).getId()).getId());

        createUserModel.setIdRol(idRol);
        createUserModel.setIdRestaurant(createUserModel.getIdRestaurant());
        userPersistencePort.saveUser(createUserModel);
    }

    @Override
    public UserModel getUserByDni(String dni) {
        return Optional.ofNullable(userPersistencePort.findUserByDni(dni))
                .orElseThrow(() -> new DomainException(ConstantException.USER_NOT_FOUND));
    }

    @Override
    public void createAccountCustomer(CreateUserModel createUserModel) {
        UseValidationUtil.isValidUser(createUserModel);
        Long idRol = rolPersistencePort.findByName("ROLE_CUSTOMER").getId();
        if(idRol == null)
            throw new DomainException(ConstantException.ROLE_NOT_FOUND);
        createUserModel.setIdRol(idRol);
        userPersistencePort.saveUser(createUserModel);
    }

    private Boolean verifyUserRol(UserModel user, String role) {
        RolModel rol = rolPersistencePort.findByName(role);
        if (rol == null) throw new DomainException(ConstantException.ROLE_NOT_FOUND);
        return user.getIdRol().equals(rol.getId());
    }

    private void validateUserRestaurant(Long restaurantId, Long userRestaurantId) {
        if (restaurantId.equals(userRestaurantId)) {
            throw new UserNotAuthorizedException();
        }
    }
}
