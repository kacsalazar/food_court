package com.foodcourt.squaremallmanagment.domain.usecase;

import com.foodcourt.squaremallmanagment.CreatorMocks;
import com.foodcourt.squaremallmanagment.domain.model.dish.DishModel;
import com.foodcourt.squaremallmanagment.domain.model.dish.DishUpdateModel;
import com.foodcourt.squaremallmanagment.domain.spi.IDishPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

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

        // No se verifica la llamada a DishValidationUtil.isValidDish
        dishUseCase.saveDish(dishModel);

        verify(dishPersistencePort).saveDish(dishModel);
    }

    @Test
    void updateDish() {
        Long id = 1L;
        DishUpdateModel updateModel = mock(DishUpdateModel.class);
        DishModel expectedModel = mock(DishModel.class);

        when(dishPersistencePort.updateDish(id, updateModel)).thenReturn(expectedModel);

        DishModel result = dishUseCase.updateDish(id, updateModel);

        verify(dishPersistencePort).updateDish(id, updateModel);
        org.junit.jupiter.api.Assertions.assertEquals(expectedModel, result);
    }
}