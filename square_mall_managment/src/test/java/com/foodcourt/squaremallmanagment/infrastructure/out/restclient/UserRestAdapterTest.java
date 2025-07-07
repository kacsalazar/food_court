package com.foodcourt.squaremallmanagment.infrastructure.out.restclient;

import com.foodcourt.squaremallmanagment.CreatorMocks;
import com.foodcourt.squaremallmanagment.domain.model.UserModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

class UserRestAdapterTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private UserRestAdapter userRestAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void isValidUser() {
        String dni = "7L";
        String role = "ROLE_OWNER";
        String expectedUrl = "http://localhost:8081/api/v1/user/verify/7L/ROLE_OWNER";

        when(restTemplate.getForObject(expectedUrl, Boolean.class)).thenReturn(true);

        Boolean result = userRestAdapter.isValidUser(dni, role);

        verify(restTemplate).getForObject(expectedUrl, Boolean.class);
        assertTrue(result);
    }


    @Test
    void ownerExists() {
        // Arrange
        String dni = "123";
        String url = "http://localhost:8081/api/v1/user/dni/123";
        UserModel expectedUser = CreatorMocks.createUserModel();

        when(restTemplate.getForObject(url, UserModel.class)).thenReturn(expectedUser);

        // Act
        UserModel result = userRestAdapter.ownerExists(dni);

        // Assert
        assertThat(result).isEqualTo(expectedUser);
        verify(restTemplate, times(2)).getForObject(url, UserModel.class); // se llama dos veces en el método
    }

    @Test
    void getUserById() {
        // Arrange
        Long id = 1L;
        String url = "http://localhost:8081/api/v1/user/id/1";
        UserModel expectedUser = CreatorMocks.createUserModel();

        when(restTemplate.getForObject(url, UserModel.class)).thenReturn(expectedUser);

        // Act
        UserModel result = userRestAdapter.getUserById(id);

        // Assert
        assertThat(result).isEqualTo(expectedUser);
        verify(restTemplate).getForObject(url, UserModel.class);
    }
}
