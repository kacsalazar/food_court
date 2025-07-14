package com.foodcourt.traceabilitymanagement.infrastructure.out.restclient;

import com.foodcourt.traceabilitymanagement.domain.model.user.EmployeeModel;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import org.junit.jupiter.api.BeforeEach;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeRestAdapterTest {

    private RestTemplate restTemplate;
    private EmployeeRestAdapter adapter;

    @BeforeEach
    void setUp() {
        restTemplate = mock(RestTemplate.class);
        adapter = new EmployeeRestAdapter(restTemplate);
    }

    @Test
    void getEmployeesByRestaurantId_shouldReturnListOfEmployees() {
        // Given
        Long restaurantId = 10L;
        String expectedUrl = "http://localhost:8081/api/v1/user/employees/" + restaurantId;

        EmployeeModel employee1 = new EmployeeModel();
        employee1.setId(1L);
        employee1.setName("Ana");

        EmployeeModel employee2 = new EmployeeModel();
        employee2.setId(2L);
        employee2.setName("Luis");

        EmployeeModel[] mockResponse = new EmployeeModel[]{employee1, employee2};

        when(restTemplate.getForObject(expectedUrl, EmployeeModel[].class)).thenReturn(mockResponse);

        // When
        List<EmployeeModel> result = adapter.getEmployeesByRestaurantId(restaurantId);

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Ana", result.get(0).getName());
        assertEquals("Luis", result.get(1).getName());

        verify(restTemplate, times(1)).getForObject(expectedUrl, EmployeeModel[].class);
    }

    @Test
    void getEmployeesByRestaurantId_shouldReturnEmptyListIfNull() {
        // Given
        Long restaurantId = 20L;
        String url = "http://localhost:8081/api/v1/user/employees/" + restaurantId;

        when(restTemplate.getForObject(url, EmployeeModel[].class)).thenReturn(null);

        // When
        List<EmployeeModel> result = adapter.getEmployeesByRestaurantId(restaurantId);

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(restTemplate, times(1)).getForObject(url, EmployeeModel[].class);
    }
}