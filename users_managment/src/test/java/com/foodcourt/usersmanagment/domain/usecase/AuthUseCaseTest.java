package com.foodcourt.usersmanagment.domain.usecase;

import com.foodcourt.usersmanagment.CreatorMocks;
import com.foodcourt.usersmanagment.domain.exception.InvalidPasswordException;
import com.foodcourt.usersmanagment.domain.exception.RolNotFoundException;
import com.foodcourt.usersmanagment.domain.model.RolModel;
import com.foodcourt.usersmanagment.domain.model.TokenModel;
import com.foodcourt.usersmanagment.domain.spi.IAuthPort;
import com.foodcourt.usersmanagment.domain.spi.IRolPersistencePort;
import com.foodcourt.usersmanagment.domain.spi.IUserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

class AuthUseCaseTest {

    @Mock
    private IAuthPort authPort;

    @InjectMocks
    private AuthUseCase authUseCase;

    @Mock
    private IUserPersistencePort userPersistencePort;

    @Mock
    private IRolPersistencePort rolPort;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loginSuccessfully() {
        when(userPersistencePort.findUserByEmail("jdoe@email.com")).thenReturn(CreatorMocks.createUserModel());
        when(rolPort.findById(1L)).thenReturn(CreatorMocks.createRolModel());
        when(authPort.verifyPassword("securePassword", "securePassword")).thenReturn(true);
        when(authPort.userLogin(CreatorMocks.createAuthModel(), CreatorMocks.createUserModel(),
                "ROLE_ADMIN")).thenReturn(CreatorMocks.createTokenModel());

        TokenModel result = authUseCase.userLogin(CreatorMocks.createAuthModel());

        assertThat(result).isNotNull();
        assertThat(result.getToken()).isEqualTo("jwtToken");

        verify(userPersistencePort).findUserByEmail("jdoe@email.com");
        verify(rolPort).findById(1L);
        verify(authPort).verifyPassword("securePassword", "securePassword");
        verify(authPort).userLogin(CreatorMocks.createAuthModel(), CreatorMocks.createUserModel(), "ROLE_ADMIN");
    }

    @Test
    void rolNotFoundException() {
        when(userPersistencePort.findUserByEmail("jdoe@email.com")).thenReturn(CreatorMocks.createUserModel());
        when(rolPort.findById(1L)).thenReturn(new RolModel(2L, null, null));

        assertThatThrownBy(() -> authUseCase.userLogin(CreatorMocks.createAuthModel()))
                .isInstanceOf(RolNotFoundException.class);

        verify(userPersistencePort).findUserByEmail("jdoe@email.com");
        verify(rolPort).findById(1L);
    }

    @Test
    void invalidPasswordException() {
        when(userPersistencePort.findUserByEmail("jdoe@email.com")).thenReturn(CreatorMocks.createUserModel());
        when(rolPort.findById(1L)).thenReturn(CreatorMocks.createRolModel());
        when(authPort.verifyPassword("securePassword", "securePassword")).thenReturn(false);

        assertThatThrownBy(() -> authUseCase.userLogin(CreatorMocks.createAuthModel()))
                .isInstanceOf(InvalidPasswordException.class);

        verify(userPersistencePort).findUserByEmail("jdoe@email.com");
        verify(rolPort).findById(1L);
        verify(authPort).verifyPassword("securePassword", "securePassword");
    }

}