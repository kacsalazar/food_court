package com.foodcourt.usersmanagment.domain.usecase;

import com.foodcourt.usersmanagment.CreatorMocks;
import com.foodcourt.usersmanagment.domain.model.OwnerModel;
import com.foodcourt.usersmanagment.domain.model.UserModel;
import com.foodcourt.usersmanagment.domain.spi.IUserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
        // Given
        OwnerModel ownerModel = CreatorMocks.createOwnerModel();

        // When
        userUseCase.saveOwner(ownerModel);

        // Then
        verify(userPersistencePort, times(1)).saveOwner(ownerModel);
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
        Long id = 1L;
        String role = "ROLE_ADMIN";

        when(userPersistencePort.verifyUserRol(id, role)).thenReturn(true);

        // When
        Boolean result = userUseCase.verifyUserRol(id, role);

        // Then
        verify(userPersistencePort, times(1)).verifyUserRol(id, role);
        assertEquals(true, result);
    }
}
