package com.foodcourt.squaremallmanagment.infrastructure.out.restclient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class UserRestAdapterTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private UserRestAdapter userRestAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void isValidUser_deberiaLlamarAlRestTemplateConUrlCorrecta() {
        String dni = "7L";
        String role = "ROLE_OWNER";
        String expectedUrl = "http://localhost:8081/api/v1/user/verify/7L/ROLE_OWNER";

        when(restTemplate.getForObject(expectedUrl, Boolean.class)).thenReturn(true);

        Boolean result = userRestAdapter.isValidUser(dni, role);

        verify(restTemplate).getForObject(expectedUrl, Boolean.class);
        assertTrue(result);
    }
}
