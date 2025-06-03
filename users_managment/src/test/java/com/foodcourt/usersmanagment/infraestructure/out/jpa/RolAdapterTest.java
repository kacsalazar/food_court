package com.foodcourt.usersmanagment.infraestructure.out.jpa;

import com.foodcourt.usersmanagment.CreatorMocks;
import com.foodcourt.usersmanagment.domain.model.RolModel;
import com.foodcourt.usersmanagment.infrastructure.out.jpa.adapter.RolAdapter;
import com.foodcourt.usersmanagment.infrastructure.out.jpa.entity.RolEntity;
import com.foodcourt.usersmanagment.infrastructure.out.jpa.mapper.IRolEntityMapper;
import com.foodcourt.usersmanagment.infrastructure.out.jpa.repository.IRolRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class RolAdapterTest {

    @Mock
    private IRolRepository rolRepository;

    @Mock
    private IRolEntityMapper rolEntityMapper;

    @InjectMocks
    private RolAdapter rolAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindByName() {
        // Given
        String name = "ROLE_ADMIN";
        RolEntity rolEntity = new RolEntity();
        RolModel rolModel = new RolModel();

        when(rolRepository.findByName(name)).thenReturn(rolEntity);
        when(rolEntityMapper.toGetRolModel(rolEntity)).thenReturn(rolModel);

        // When
        RolModel result = rolAdapter.findByName(name);

        // Then
        verify(rolRepository, times(1)).findByName(name);
        verify(rolEntityMapper, times(1)).toGetRolModel(rolEntity);
        assertEquals(rolModel, result);
    }

    @Test
    void testFindById() {
        // Given
        Long id = 1L;
        RolEntity rolEntity = CreatorMocks.createRolEntity();
        RolModel rolModel = CreatorMocks.createRolModel();

        when(rolRepository.findById(id)).thenReturn(java.util.Optional.of(rolEntity));
        when(rolEntityMapper.toGetRolModel(rolEntity)).thenReturn(rolModel);

        // When
        RolModel result = rolAdapter.findById(id);

        // Then
        verify(rolRepository, times(1)).findById(id);
        verify(rolEntityMapper, times(1)).toGetRolModel(rolEntity);
        assertEquals(rolModel, result);
    }
}
