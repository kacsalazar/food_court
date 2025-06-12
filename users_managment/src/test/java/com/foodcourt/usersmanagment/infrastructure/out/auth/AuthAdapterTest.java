package com.foodcourt.usersmanagment.infrastructure.out.auth;

import com.foodcourt.usersmanagment.application.handler.ITokenValidator;
import com.foodcourt.usersmanagment.domain.model.AuthModel;
import com.foodcourt.usersmanagment.domain.model.ClaimUserModel;
import com.foodcourt.usersmanagment.domain.model.RolModel;
import com.foodcourt.usersmanagment.domain.model.TokenModel;
import com.foodcourt.usersmanagment.infrastructure.out.jpa.adapter.RolAdapter;
import com.foodcourt.usersmanagment.infrastructure.out.jpa.entity.UserEntity;
import com.foodcourt.usersmanagment.infrastructure.out.jpa.repository.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AuthAdapterTest {


    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private IUserRepository userRepository;
    @Mock
    private ITokenValidator iTokenValidator;
    @Mock
    private RolAdapter rolAdapter;

    @InjectMocks
    private AuthAdapter authAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void userLogin_Success() {
        // Arrange
        AuthModel authModel = new AuthModel("plainPassword", "test@mail.com");
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("test@mail.com");
        userEntity.setPassword("hashedPassword");
        userEntity.setName("Test");
        userEntity.setId(1L);
        userEntity.setDni("123456");
        userEntity.setIdRol(2L);

        when(userRepository.findUserByEmail("test@mail.com")).thenReturn(userEntity);
        when(passwordEncoder.matches("plainPassword", "hashedPassword")).thenReturn(true);

        RolModel rolModel = new RolModel();
        rolModel.setName("ROLE_USER");
        when(rolAdapter.findById(2L)).thenReturn(rolModel);

        when(iTokenValidator.generateToken(any(ClaimUserModel.class))).thenReturn("jwt-token");

        // Act
        TokenModel result = authAdapter.userLogin(authModel);

        // Assert
        assertNotNull(result);
        assertEquals("jwt-token", result.getToken());
        verify(userRepository).findUserByEmail("test@mail.com");
        verify(passwordEncoder).matches("plainPassword", "hashedPassword");
        verify(rolAdapter).findById(2L);
        verify(iTokenValidator).generateToken(any(ClaimUserModel.class));
    }

    @Test
    void userLogin_InvalidPassword_ThrowsException() {
        // Arrange
        AuthModel authModel = new AuthModel("wrongPassword", "test@mail.com");
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("test@mail.com");
        userEntity.setPassword("hashedPassword");

        when(userRepository.findUserByEmail("test@mail.com")).thenReturn(userEntity);
        when(passwordEncoder.matches("wrongPassword", "hashedPassword")).thenReturn(false);

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            authAdapter.userLogin(authModel);
        });
        assertEquals("Invalid password", exception.getMessage());
        verify(userRepository).findUserByEmail("test@mail.com");
        verify(passwordEncoder).matches("wrongPassword", "hashedPassword");
        verifyNoMoreInteractions(rolAdapter, iTokenValidator);
    }

    @Test
    void findUserByEmail_ReturnsUser() {
        // Arrange
        UserEntity userEntity = new UserEntity();
        when(userRepository.findUserByEmail("mail@mail.com")).thenReturn(userEntity);

        // Act
        UserEntity result = authAdapter.findUserByEmail("mail@mail.com");

        // Assert
        assertEquals(userEntity, result);
        verify(userRepository).findUserByEmail("mail@mail.com");
    }
}