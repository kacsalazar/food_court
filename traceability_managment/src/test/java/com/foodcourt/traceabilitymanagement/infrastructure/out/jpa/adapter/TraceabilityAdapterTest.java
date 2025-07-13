package com.foodcourt.traceabilitymanagement.infrastructure.out.jpa.adapter;

import com.foodcourt.traceabilitymanagement.domain.model.TraceabilityModel;
import com.foodcourt.traceabilitymanagement.domain.usecase.util.StatusEnum;
import com.foodcourt.traceabilitymanagement.infrastructure.out.jpa.entity.TraceabilityEntity;
import com.foodcourt.traceabilitymanagement.infrastructure.out.jpa.mapper.TraceabilityEntityMapper;
import com.foodcourt.traceabilitymanagement.infrastructure.out.jpa.repository.ITraceabilityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

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
    void findAllTracesByOrderId_shouldReturnMappedModels() {
        Long orderId = 1L;
        TraceabilityEntity entity =TraceabilityEntity.builder().build();
        entity.setId("100");

        when(traceabilityRepository.findAllByOrderId(orderId)).thenReturn(List.of(entity));

        List<TraceabilityModel> result = traceabilityAdapter.findAllTracesByOrderId(orderId);

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void findAllByOrderIdAndStatus_shouldFilterAndMapCorrectly() {
        Long orderId = 2L;
        TraceabilityEntity entity = TraceabilityEntity.builder().build();
        entity.setNewState(StatusEnum.IN_PROGRESS.name());

        when(traceabilityRepository.findByOrderIdAndNewStateIn(eq(orderId), anyCollection())).thenReturn(List.of(entity));

        List<TraceabilityModel> result = traceabilityAdapter.findAllByOrderIdAndStatus(orderId);

        assertNotNull(result);
        assertEquals(1, result.size());
    }

}