package com.foodcourt.squaremallmanagment.infrastructure.out.jpa.adapter;

import static org.junit.jupiter.api.Assertions.*;

import com.foodcourt.squaremallmanagment.domain.model.TraceabilityModel;
import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.entity.TraceabilityEntity;
import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.repository.ITraceabilityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.*;

class TraceabilityAdapterTest {

    @Mock
    private ITraceabilityRepository traceabilityRepository;

    @InjectMocks
    private TraceabilityAdapter traceabilityAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveTraceability_shouldCallRepositoryWithMappedEntity() {
        // Arrange
        TraceabilityModel model = TraceabilityModel.builder()
                .orderId(1L)
                .customerId(2L)
                .emailCustomer("customer@mail.com")
                .date(LocalDateTime.now())
                .beforeState("PENDING")
                .newState("IN_PROGRESS")
                .employeeId(3L)
                .employeeEmail("employee@mail.com")
                .build();

        // Act
        traceabilityAdapter.saveTraceability(model);

        // Assert
        ArgumentCaptor<TraceabilityEntity> captor = ArgumentCaptor.forClass(TraceabilityEntity.class);
        verify(traceabilityRepository, times(1)).save(captor.capture());

        TraceabilityEntity savedEntity = captor.getValue();
        assertEquals(model.getOrderId(), savedEntity.getOrderId());
        assertEquals(model.getEmailCustomer(), savedEntity.getEmailCustomer());
        assertEquals(model.getNewState(), savedEntity.getNewState());
    }

    @Test
    void findAllTracesByOrderId_shouldReturnMappedModels() {
        // Arrange
        Long orderId = 10L;

        TraceabilityEntity entity = mock(TraceabilityEntity.class);
        when(entity.getOrderId()).thenReturn(orderId);
        when(entity.getNewState()).thenReturn("DELIVERED");
        when(entity.getBeforeState()).thenReturn("IN_PROGRESS");
        when(entity.getCustomerId()).thenReturn(1L);
        when(entity.getEmployeeId()).thenReturn(2L);
        when(entity.getEmailCustomer()).thenReturn("cliente@dominio.com");
        when(entity.getEmployeeEmail()).thenReturn("empleado@dominio.com");
        when(entity.getDate()).thenReturn(LocalDateTime.now());

        when(traceabilityRepository.findAllByOrderId(orderId)).thenReturn(List.of(entity));

        List<TraceabilityModel> result = traceabilityAdapter.findAllTracesByOrderId(orderId);

        assertEquals(1, result.size());
        assertEquals("DELIVERED", result.get(0).getNewState());
    }

    @Test
    void findAllByOrderIdAndStatus_shouldReturnFilteredStates() {
        Long orderId = 20L;

        TraceabilityEntity entity = mock(TraceabilityEntity.class);
        when(entity.getOrderId()).thenReturn(orderId);
        when(entity.getNewState()).thenReturn("IN_PROGRESS"); // <-- aquí es clave
        when(entity.getBeforeState()).thenReturn("PENDING");
        when(entity.getCustomerId()).thenReturn(1L);
        when(entity.getEmployeeId()).thenReturn(2L);
        when(entity.getEmailCustomer()).thenReturn("cliente@correo.com");
        when(entity.getEmployeeEmail()).thenReturn("empleado@correo.com");
        when(entity.getDate()).thenReturn(LocalDateTime.now());

        when(traceabilityRepository.findByOrderIdAndStates(
                eq(orderId), eq(List.of("IN_PROGRESS", "DELIVERED"))))
                .thenReturn(List.of(entity));

        List<TraceabilityModel> result = traceabilityAdapter.findAllByOrderIdAndStatus(orderId);

        assertEquals(1, result.size());
        assertEquals("IN_PROGRESS", result.get(0).getNewState());
    }

}