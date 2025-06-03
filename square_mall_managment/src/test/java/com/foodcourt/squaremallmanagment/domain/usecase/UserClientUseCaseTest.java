package com.foodcourt.squaremallmanagment.domain.usecase;

import com.foodcourt.squaremallmanagment.domain.spi.IUserClientPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserClientUseCaseTest {

    @Mock
    private IUserClientPort userClientPort;

    @InjectMocks
    private UserClientUseCase userClientUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void isValidUserTest() {
        Long userId = 10L;
        String rol = "ROLE_OWNER";
        when(userClientPort.isValidUser(userId, rol)).thenReturn(true);

        Boolean result = userClientUseCase.isValidUser(userId, rol);

        verify(userClientPort).isValidUser(userId, rol);
        assertTrue(result);
    }
}
