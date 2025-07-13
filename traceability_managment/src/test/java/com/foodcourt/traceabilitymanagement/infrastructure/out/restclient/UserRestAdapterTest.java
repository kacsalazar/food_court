package com.foodcourt.traceabilitymanagement.infrastructure.out.restclient;

import static org.junit.jupiter.api.Assertions.*;

import com.foodcourt.traceabilitymanagement.domain.model.user.UserModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.*;

class UserRestAdapterTest {

    private RestTemplate restTemplate;
    private UserRestAdapter userRestAdapter;

    @BeforeEach
    void setUp() {
        restTemplate = mock(RestTemplate.class);
        userRestAdapter = new UserRestAdapter(restTemplate);
    }

    @Test
    void ownerExists_shouldReturnUserModel() {
        // Arrange
        String dni = "123456";
        String expectedUrl = "http://localhost:8081/api/v1/user/dni/" + dni;

        UserModel mockUser = new UserModel();
        mockUser.setId(88L);
        mockUser.setDni(dni);

        when(restTemplate.getForObject(expectedUrl, UserModel.class)).thenReturn(mockUser);

        // Act
        UserModel result = userRestAdapter.ownerExists(dni);

        // Assert
        assertNotNull(result);
        assertEquals(88L, result.getId());
        assertEquals(dni, result.getDni());
    }

    @Test
    void ownerExists_shouldReturnNullIfNotFound() {
        // Arrange
        String dni = "notfound";
        String expectedUrl = "http://localhost:8081/api/v1/user/dni/" + dni;

        when(restTemplate.getForObject(expectedUrl, UserModel.class)).thenReturn(null);

        // Act
        UserModel result = userRestAdapter.ownerExists(dni);

        // Assert
        assertNull(result);
    }

}