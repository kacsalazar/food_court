package com.foodcourt.usersmanagment.domain.usecase;

import com.foodcourt.usersmanagment.domain.model.AuthModel;
import com.foodcourt.usersmanagment.domain.model.TokenModel;
import com.foodcourt.usersmanagment.domain.spi.IAuthPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AuthUseCaseTest {


    @Mock
    private IAuthPort authPort;

    private AuthUseCase authUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        authUseCase = new AuthUseCase(authPort);
    }

    @Test
    void userLogin_DelegatesToAuthPortAndReturnsTokenModel() {
        // Arrange
        AuthModel authModel = new AuthModel();
        TokenModel tokenModel = new TokenModel();
        when(authPort.userLogin(authModel)).thenReturn(tokenModel);

        // Act
        TokenModel result = authUseCase.userLogin(authModel);

        // Assert
        assertEquals(tokenModel, result);
        verify(authPort, times(1)).userLogin(authModel);
    }


}