package com.foodcourt.usersmanagment.domain.usecase;

import com.foodcourt.usersmanagment.CreatorMocks;
import com.foodcourt.usersmanagment.domain.model.SaveUserModel;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        SaveUserModel saveUserModel = new SaveUserModel();
        saveUserModel.setEmail("test@mail.com");
        saveUserModel.setPhoneNumber("3136871");
        saveUserModel.setPassword("hashedPassword");
        saveUserModel.setName("Test");
        saveUserModel.setDni("123456");
        saveUserModel.setBirthdayDate(date);


        // When
        userUseCase.saveOwner(saveUserModel);

        // Then
        verify(userPersistencePort, times(1)).saveOwner(saveUserModel);
    }

    @Test
    void testFindUserById() {
        // Given
        Long id = 1L;
        UserModel userModel = CreatorMocks.createUserModel();

        when(userPersistencePort.findUserById(id)).thenReturn(userModel);

        // When
        UserModel result = userUseCase.findUserById(id);

        // Then
        verify(userPersistencePort, times(1)).findUserById(id);
        assertEquals(userModel, result);
    }

    @Test
    void testVerifyUserRol() {
        // Given
        String id = "1L";
        String role = "ROLE_ADMIN";

        when(userPersistencePort.verifyUserRol(id, role)).thenReturn(true);

        // When
        Boolean result = userUseCase.verifyUserRol(id, role);

        // Then
        verify(userPersistencePort, times(1)).verifyUserRol(id, role);
        assertEquals(true, result);
    }


    @Test
    void createAccountEmployee() {
        // Arrange
        SaveUserModel saveUserModel = CreatorMocks.createOwnerModel();
        // Puedes mockear UseValidationUtil si es estático usando PowerMockito, aquí solo se verifica la llamada al persistence port

        // Act
        userUseCase.createAccountEmployee(saveUserModel);

        // Assert
        verify(userPersistencePort, times(1)).createAccountEmployee(saveUserModel);
    }

    @Test
    void findUserById() {
        // Arrange
        String dni = "123456";
        UserModel userModel = new UserModel();
        when(userPersistencePort.findUserByDni(dni)).thenReturn(userModel);

        // Act
        UserModel result = userUseCase.findUserById(dni);

        // Assert
        assertEquals(userModel, result);
        verify(userPersistencePort, times(1)).findUserByDni(dni);
    }

}
