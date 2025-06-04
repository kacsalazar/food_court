package com.foodcourt.squaremallmanagment.infrastructure.out.jpa.adapter;

import com.foodcourt.squaremallmanagment.CreatorMocks;
import com.foodcourt.squaremallmanagment.domain.model.DishModel;
import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.entity.DishEntity;
import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.mapper.IDishEntityMapper;
import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.repository.IDishRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

class DishAdapterTest {

    @Mock
    private IDishRepository dishRepository;

    @Mock
    private IDishEntityMapper dishMapper;

    @InjectMocks
    private DishAdapter dishAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveDish() {
        DishModel dishModel = CreatorMocks.createDishModel();

        DishEntity dishEntity = CreatorMocks.createDishEntity();
        when(dishMapper.toDishEntity(dishModel)).thenReturn(dishEntity);

        dishAdapter.saveDish(dishModel);

        assertTrue(dishEntity.getIsActive());
        verify(dishMapper).toDishEntity(dishModel);
        verify(dishRepository).save(dishEntity);
    }
}