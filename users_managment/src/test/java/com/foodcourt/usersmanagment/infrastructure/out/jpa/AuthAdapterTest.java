package com.foodcourt.usersmanagment.infrastructure.out.jpa;

import com.foodcourt.usersmanagment.CreatorMocks;
import com.foodcourt.usersmanagment.application.handler.ITokenValidator;
import com.foodcourt.usersmanagment.domain.model.ClaimUserModel;
import com.foodcourt.usersmanagment.domain.model.TokenModel;
import com.foodcourt.usersmanagment.infrastructure.out.auth.AuthAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AuthAdapterTest {


    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private ITokenValidator iTokenValidator;

    @InjectMocks
    private AuthAdapter authAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loginSuccessful() {
        // Arrange
        String rolName = "OWNER";
        String expectedToken = "mocked-jwt-token";

        when(iTokenValidator.generateToken(any(ClaimUserModel.class))).thenReturn(expectedToken);

        // Act
        TokenModel result = authAdapter.userLogin(CreatorMocks.createAuthModel(), CreatorMocks.createUserModel(), rolName);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getToken()).isEqualTo(expectedToken);
        verify(iTokenValidator).generateToken(any(ClaimUserModel.class));
    }

    @Test
    void verifyPasswordSuccessfully() {
        // Arrange
        String rawPassword = "plaintextpassword";
        String encodedPassword = "$2a$10$encodedValue";

        when(passwordEncoder.matches(rawPassword, encodedPassword)).thenReturn(true);

        // Act
        boolean result = authAdapter.verifyPassword(rawPassword, encodedPassword);

        // Assert
        assertThat(result).isTrue();
        verify(passwordEncoder).matches(rawPassword, encodedPassword);
    }

    @Test
    void passwordsDoNotMatch() {
        // Arrange
        String rawPassword = "wrongpassword";
        String encodedPassword = "$2a$10$encodedValue";

        when(passwordEncoder.matches(rawPassword, encodedPassword)).thenReturn(false);

        // Act
        boolean result = authAdapter.verifyPassword(rawPassword, encodedPassword);

        // Assert
        assertThat(result).isFalse();
        verify(passwordEncoder).matches(rawPassword, encodedPassword);
    }
}