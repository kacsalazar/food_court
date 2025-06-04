package com.foodcourt.squaremallmanagment.domain.usecase;

import com.foodcourt.squaremallmanagment.CreatorMocks;
import com.foodcourt.squaremallmanagment.domain.model.DishModel;
import com.foodcourt.squaremallmanagment.domain.model.DishUpdateModel;
import com.foodcourt.squaremallmanagment.domain.spi.IDishPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DishUseCaseTest {

    @Mock
    private IDishPersistencePort dishPersistencePort;

    @InjectMocks
    private DishUseCase dishUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveDish() {
        DishModel dishModel = CreatorMocks.createDishModel();
        dishUseCase.saveDish(dishModel);
        verify(dishPersistencePort).saveDish(dishModel);
    }

    void updateDish() {
        Long id = 1L;
        DishUpdateModel updateModel = CreatorMocks.createDishUpdateModel();
        DishModel expectedModel = CreatorMocks.createDishModel();

        when(dishPersistencePort.updateDish(id, updateModel)).thenReturn(expectedModel);

        DishModel result = dishUseCase.updateDish(id, updateModel);

        verify(dishPersistencePort).updateDish(id, updateModel);
        assertEquals(expectedModel, result);
    }
}