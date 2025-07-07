package com.foodcourt.usersmanagment.domain.usecase;

import com.foodcourt.usersmanagment.CreatorMocks;
import com.foodcourt.usersmanagment.domain.exception.DomainException;
import com.foodcourt.usersmanagment.domain.exception.RolNotFoundException;
import com.foodcourt.usersmanagment.domain.exception.UserNotAuthorizedException;
import com.foodcourt.usersmanagment.domain.model.CreateUserModel;
import com.foodcourt.usersmanagment.domain.model.RolModel;
import com.foodcourt.usersmanagment.domain.model.UserModel;
import com.foodcourt.usersmanagment.domain.spi.IRestaurantClientPort;
import com.foodcourt.usersmanagment.domain.spi.IRolPersistencePort;
import com.foodcourt.usersmanagment.domain.spi.IUserPersistencePort;
import com.foodcourt.usersmanagment.infrastructure.exceptionhandler.ExceptionResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.util.Collections;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.*;

class UserUseCaseTest {

    @Mock
    private IUserPersistencePort userPersistencePort;
    
    @InjectMocks
    private UserUseCase userUseCase;

    @Mock
    private IRestaurantClientPort restaurantClientPort;

    @Mock
    private IRolPersistencePort rolPersistencePort;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveOwnerSuccessfully() throws ParseException {
        when(rolPersistencePort.findByName("ROLE_OWNER")).thenReturn(new RolModel(2L, "ROLE_OWNER",
                ""));

        CreateUserModel modelToSave = CreatorMocks.createSaveUserModel();

        userUseCase.saveOwner(modelToSave);

        verify(userPersistencePort).saveUser(modelToSave);
    }

    @Test
    void exceptionWhenOwnerRoleNotFound() {
        when(rolPersistencePort.findByName("ROLE_OWNER")).thenReturn(new RolModel(null, null, null));

        assertThatThrownBy(() -> userUseCase.saveOwner(CreatorMocks.createSaveUserModel()))
                .isInstanceOf(RolNotFoundException.class);
                //.hasMessage(ExceptionResponse.ROL_NOT_FOUND.getMessage());
    }

    @Test
    void findUserById() {
        when(userPersistencePort.findUserById(1L)).thenReturn(CreatorMocks.createUserModel());

        UserModel result = userUseCase.findUserById(1L);

        assertThat(result).isEqualTo(CreatorMocks.createUserModel());
    }

    @Test
    void exceptionWhenUserNotFound() {
        when(userPersistencePort.findUserById(1L)).thenReturn(null);

        assertThatThrownBy(() -> userUseCase.findUserById(1L))
                .isInstanceOf(DomainException.class);
                //.hasMessage(ExceptionResponse.USER_NOT_FOUND.getMessage());
    }

    @Test
    void verifyUserRoleCorrectly() {

        when(userPersistencePort.findUserByDni("234")).thenReturn(CreatorMocks.createEmployeeModel());
        when(rolPersistencePort.findByName("ROLE_EMPLOYEE")).thenReturn(new RolModel(2L, "ROLE_EMPLOYEE", ""));

        Boolean result = userUseCase.verifyUserRol("234", "ROLE_EMPLOYEE");

        assertThat(result).isTrue();
    }

    @Test
    void verifyUserRoleWhenUserNotFound() {
        when(userPersistencePort.findUserByDni("123")).thenReturn(null);

        assertThatThrownBy(() -> userUseCase.verifyUserRol("123", "ROLE_EMPLOYEE"))
                .isInstanceOf(DomainException.class)
                .hasMessage(ExceptionResponse.USER_NOT_FOUND.getMessage());
    }

    @Test
    void createAccountCustomer() throws ParseException {
        when(rolPersistencePort.findByName("ROLE_CUSTOMER")).thenReturn(new RolModel(4L, "ROLE_CUSTOMER", ""));

        userUseCase.createAccountCustomer(CreatorMocks.createCustomerModel());

        verify(userPersistencePort).saveUser(CreatorMocks.createCustomerModel());
    }

    @Test
    void roleNotFound() {
        when(rolPersistencePort.findByName("ROLE_CUSTOMER")).thenReturn(new RolModel(null, null, null));

        assertThatThrownBy(() -> userUseCase.createAccountCustomer(CreatorMocks.createCustomerModel()))
                .isInstanceOf(RolNotFoundException.class);
                //.hasMessage(ExceptionResponse.ROL_NOT_FOUND.getMessage());
    }

    @Test
    void useNotAuthorizedWhenIdsMatch() {
        when(userPersistencePort.findUserByDni("234")).thenReturn(CreatorMocks.createCreateOwnerModel());
        when(restaurantClientPort.getRestaurantIdByOwner(1L)).thenReturn(CreatorMocks.createRestaurantModel()); // ambos con mismo ID restaurante
        when(rolPersistencePort.findByName("ROLE_EMPLOYEE")).
                thenReturn(new RolModel(2L, "ROLE_EMPLOYEE", ""));

        assertThatThrownBy(() -> userUseCase.createAccountEmployee(CreatorMocks.createCreateEmployeeModel(), "234"))
                .isInstanceOf(UserNotAuthorizedException.class);
    }

    @Test
    void findEmployeesByRestaurant_id() {
        when(userPersistencePort.findEmployeeByRestaurantId(1L)).thenReturn(List.of(CreatorMocks.createUserModel()));

        List<UserModel> result = userUseCase.findEmployeeByRestaurantId(1L);

        assertThat(result).hasSize(1).contains(CreatorMocks.createUserModel());
    }

    @Test
    void exceptionEmployeeListEmpty() {
        when(userPersistencePort.findEmployeeByRestaurantId(1L)).thenReturn(Collections.emptyList());

        assertThatThrownBy(() -> userUseCase.findEmployeeByRestaurantId(1L))
                .isInstanceOf(DomainException.class)
                .hasMessage(ExceptionResponse.USER_NOT_FOUND.getMessage());
    }

    @Test
    void userByDni() {
        when(userPersistencePort.findUserByDni("123")).thenReturn(CreatorMocks.createUserModel());

        UserModel result = userUseCase.getUserByDni("123");

        assertThat(result).isEqualTo(CreatorMocks.createUserModel());
    }

    @Test
    void exceptionUserNotFoundByDni() {
        when(userPersistencePort.findUserByDni("123")).thenReturn(null);

        assertThatThrownBy(() -> userUseCase.getUserByDni("123"))
                .isInstanceOf(DomainException.class)
                .hasMessage(ExceptionResponse.USER_NOT_FOUND.getMessage());
    }

}
