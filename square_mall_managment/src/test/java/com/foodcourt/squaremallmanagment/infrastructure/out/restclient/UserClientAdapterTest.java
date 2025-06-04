package com.foodcourt.squaremallmanagment.infrastructure.out.restclient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class UserClientAdapterTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private UserClientAdapter userClientAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void isValidUser() {
        Long userId = 7L;
        String role = "ROLE_OWNER";
        String expectedUrl = "http://localhost:8081/api/v1/user/verify/7/ROLE_OWNER";

        when(restTemplate.getForObject(expectedUrl, Boolean.class)).thenReturn(true);

        Boolean result = userClientAdapter.isValidUser(userId, role);

        verify(restTemplate).getForObject(expectedUrl, Boolean.class);
        assertTrue(result);
    }
}
