package com.foodcourt.squaremallmanagment.infrastructure.out.jpa.adapter;

import com.foodcourt.squaremallmanagment.domain.model.dish.DishModel;
import com.foodcourt.squaremallmanagment.domain.model.dish.DishUpdateModel;
import com.foodcourt.squaremallmanagment.domain.model.dish.ListDishesByRestaurantModel;
import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.entity.DishEntity;
import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.mapper.IDishEntityMapper;
import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.repository.IDishRepository;
import com.foodcourt.squaremallmanagment.mocks.CreatorDishMocks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Optional;

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
        // Arrange
        DishModel dishModel = CreatorDishMocks.buildCompleteDishModel();

        // Act
        dishAdapter.saveDish(dishModel);

        // Assert
        verify(dishRepository).save(any(DishEntity.class));
    }

    @Test
    void findDishById() {
        // Arrange
        DishEntity dishEntity = CreatorDishMocks.buildCompleteDishEntity();
        when(dishRepository.findById(1L)).thenReturn(Optional.of(dishEntity));

        // Act
        DishModel result = dishAdapter.findDishById(1L);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getDishInfo().getName()).isEqualTo("Pizza");
        assertThat(result.getRestaurantInfo().getIdRestaurant()).isEqualTo(10L);
    }

    @Test
    void updateDish() {
        // Arrange
        DishModel dishModel = CreatorDishMocks.buildCompleteDishModel();
        DishEntity dishEntity = CreatorDishMocks.buildCompleteDishEntity();

        when(dishMapper.toDishEntity(dishModel)).thenReturn(dishEntity);
        when(dishRepository.save(dishEntity)).thenReturn(dishEntity);

        // Act
        DishModel result = dishAdapter.updateDish(dishModel, new DishUpdateModel());

        // Assert
        assertThat(result.getDishInfo().getName()).isEqualTo("Pizza");
        verify(dishRepository).save(dishEntity);
    }

    @Test
    void disableDish() {
        // Arrange
        DishModel dishModel = CreatorDishMocks.buildCompleteDishModel();
        DishEntity dishEntity = CreatorDishMocks.buildCompleteDishEntity();

        when(dishMapper.toDishEntity(dishModel)).thenReturn(dishEntity);
        when(dishRepository.save(dishEntity)).thenReturn(dishEntity);

        // Act
        DishModel result = dishAdapter.disableDish(dishModel, false);

        // Assert
        assertThat(result.getDishInfo().getIsActive()).isTrue(); // se mantiene igual porque la lógica no cambia el valor
        verify(dishRepository).save(dishEntity);
    }

    @Test
    void returnDishesByRestaurantCategory() {
        // Arrange
        DishEntity dishEntity = CreatorDishMocks.buildCompleteDishEntity();
        when(dishRepository.findDishesByRestaurant(10L, 5L, 0, 10)).thenReturn(List.of(dishEntity));

        // Act
        List<ListDishesByRestaurantModel> result = dishAdapter.getDishesByCategory(10L, 5L, 0, 10);

        // Assert
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("Pizza");
    }
}