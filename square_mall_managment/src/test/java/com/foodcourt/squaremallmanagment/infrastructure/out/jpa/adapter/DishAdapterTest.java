package com.foodcourt.squaremallmanagment.infrastructure.out.jpa.adapter;

import com.foodcourt.squaremallmanagment.CreatorMocks;
import com.foodcourt.squaremallmanagment.domain.model.DishModel;
import com.foodcourt.squaremallmanagment.domain.model.DishUpdateModel;
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

    @Test
    void findDishById() {
        Long id = 1L;
        DishEntity entity = CreatorMocks.createDishEntity();
        DishModel model = CreatorMocks.createDishModel();

        when(dishRepository.findById(id)).thenReturn(java.util.Optional.of(entity));
        when(dishMapper.toDishModel(entity)).thenReturn(model);

        DishModel result = dishAdapter.findDishById(id);

        assertEquals(model, result);
        verify(dishRepository).findById(id);
        verify(dishMapper).toDishModel(entity);
    }

    @Test
    void updateDish() {
        Long id = 1L;
        DishEntity entity = CreatorMocks.createDishEntity();
        DishUpdateModel updateModel = new DishUpdateModel();
        updateModel.setDescription("Nueva descripción");
        updateModel.setPrice(20.0);

        DishEntity updatedEntity = CreatorMocks.createDishEntity();
        updatedEntity.setDescription("Nueva descripción");
        updatedEntity.setPrice(20.0);

        DishModel updatedModel = CreatorMocks.createDishModel();
        updatedModel.setDescription("Nueva descripción");
        updatedModel.setPrice(20.0);

        when(dishRepository.findById(id)).thenReturn(java.util.Optional.of(entity));
        when(dishRepository.save(entity)).thenReturn(updatedEntity);
        when(dishMapper.toDishModel(updatedEntity)).thenReturn(updatedModel);

        DishModel result = dishAdapter.updateDish(id, updateModel);

        assertEquals(updatedModel, result);
        assertEquals("Nueva descripción", entity.getDescription());
        assertEquals(20.0, entity.getPrice());
        verify(dishRepository).findById(id);
        verify(dishRepository).save(entity);
        verify(dishMapper).toDishModel(updatedEntity);
    }
}