package com.foodcourt.usersmanagment.domain.usecase;

import com.foodcourt.usersmanagment.CreatorMocks;
import com.foodcourt.usersmanagment.domain.exception.ConstantException;
import com.foodcourt.usersmanagment.domain.exception.DomainException;
import com.foodcourt.usersmanagment.domain.model.CreateUserModel;
import com.foodcourt.usersmanagment.domain.model.UserModel;
import com.foodcourt.usersmanagment.domain.spi.IUserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class UserUseCaseTest {

    @Mock
    private IUserPersistencePort userPersistencePort;
    
    @InjectMocks
    private UserUseCase userUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveOwner() {

        LocalDate localDate = LocalDate.parse("1998-08-12");
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        // Given
        CreateUserModel createUserModel = new CreateUserModel();
        createUserModel.setEmail("test@mail.com");
        createUserModel.setPhoneNumber("3136871");
        createUserModel.setPassword("hashedPassword");
        createUserModel.setName("Test");
        createUserModel.setDni("123456");
        createUserModel.setBirthdayDate(date);


        // When
        userUseCase.saveOwner(createUserModel);

        // Then
        verify(userPersistencePort, times(1)).saveUser(createUserModel);
    }

    @Test
    void findUserByIdTest() {
        Long id = 1L;
        UserModel userModel = new UserModel();
        when(userPersistencePort.findUserById(id)).thenReturn(userModel);

        UserModel result = userUseCase.findUserById(id);

        assertEquals(userModel, result);
        verify(userPersistencePort, times(1)).findUserById(id);
    }

    @Test
    void findUserByIdThrowsExceptionTest() {
        Long id = 1L;
        when(userPersistencePort.findUserById(id)).thenReturn(null);

        DomainException exception = assertThrows(DomainException.class, () -> {
            userUseCase.findUserById(id);
        });

        assertEquals(ConstantException.USER_NOT_FOUND, exception.getMessage());
        verify(userPersistencePort, times(1)).findUserById(id);
    }

    @Test
    void verifyUserRolTest() {
        String dni = "123";
        String role = "ROLE_ADMIN";
        //when(userPersistencePort.verifyUserRol(dni, role)).thenReturn(true);

        Boolean result = userUseCase.verifyUserRol(dni, role);

        assertTrue(result);
        //verify(userPersistencePort, times(1)).verifyUserRol(dni, role);
    }

    @Test
    void verifyUserRolThrowsExceptionTest() {
        String dni = "123";
        String role = "ROLE_ADMIN";
        //when(userPersistencePort.verifyUserRol(dni, role)).thenReturn(null);

        DomainException exception = assertThrows(DomainException.class, () -> {
            userUseCase.verifyUserRol(dni, role);
        });

        assertEquals(ConstantException.INVALID_USER, exception.getMessage());
        //verify(userPersistencePort, times(1)).verifyUserRol(dni, role);
    }


    @Test
    void createAccountEmployee() {
        // Arrange
        CreateUserModel createUserModel = CreatorMocks.createOwnerModel();

        // Act
        //userUseCase.createAccountEmployee(createUserModel);

        // Assert
       // verify(userPersistencePort, times(1)).createAccountEmployee(createUserModel);
    }

    @Test
    void getUserByDni() {
        String dni = "123456";
        UserModel userModel = new UserModel();
        when(userPersistencePort.findUserByDni(dni)).thenReturn(userModel);

        UserModel result = userUseCase.getUserByDni(dni);

        assertEquals(userModel, result);
        verify(userPersistencePort, times(1)).findUserByDni(dni);
    }

    @Test
    void getUserByDniThrowsException() {
        String dni = "123456";
        when(userPersistencePort.findUserByDni(dni)).thenReturn(null);

        DomainException exception = assertThrows(DomainException.class, () -> {
            userUseCase.getUserByDni(dni);
        });

        assertEquals(ConstantException.USER_NOT_FOUND, exception.getMessage());
        verify(userPersistencePort, times(1)).findUserByDni(dni);
    }

}
