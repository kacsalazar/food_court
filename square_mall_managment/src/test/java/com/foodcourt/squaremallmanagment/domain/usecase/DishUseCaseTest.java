package com.foodcourt.squaremallmanagment.domain.usecase;

import com.foodcourt.squaremallmanagment.domain.model.UserModel;
import com.foodcourt.squaremallmanagment.domain.model.dish.DishModel;
import com.foodcourt.squaremallmanagment.domain.model.dish.DishUpdateModel;
import com.foodcourt.squaremallmanagment.domain.model.dish.ListDishesByRestaurantModel;
import com.foodcourt.squaremallmanagment.domain.model.restaurant.RestaurantModel;
import com.foodcourt.squaremallmanagment.domain.spi.IDishPersistencePort;
import com.foodcourt.squaremallmanagment.domain.spi.IRestaurantPersistencePort;
import com.foodcourt.squaremallmanagment.domain.spi.IUserClientPort;
import com.foodcourt.squaremallmanagment.domain.exception.DishNotFoundException;
import com.foodcourt.squaremallmanagment.domain.exception.InvalidUserException;
import com.foodcourt.squaremallmanagment.domain.exception.RestaurantNotFoundException;
import com.foodcourt.squaremallmanagment.domain.exception.UserNotFoundException;
import com.foodcourt.squaremallmanagment.mocks.CreatorDishMocks;
import com.foodcourt.squaremallmanagment.mocks.CreatorMocksRestaurant;
import com.foodcourt.squaremallmanagment.mocks.CreatorMocksUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class DishUseCaseTest {

    //@Mock
    private IDishPersistencePort dishPersistencePort;
    //@Mock
    private IUserClientPort userClientPort;
    //@Mock
    private IRestaurantPersistencePort restaurantPersistencePort;

    //@InjectMocks
    private DishUseCase dishUseCase;

    @BeforeEach
    void setUp() {
        dishPersistencePort = mock(IDishPersistencePort.class);
        userClientPort = mock(IUserClientPort.class);
        restaurantPersistencePort = mock(IRestaurantPersistencePort.class);
        dishUseCase = new DishUseCase(dishPersistencePort, userClientPort, restaurantPersistencePort);

    }

    @Test
    void saveDish() {
        // Arrange
        DishModel dish = CreatorDishMocks.buildCompleteDishModel(); // idRestaurant = 1L
        String dniOwner = "123";
        Long idOwner = 1L;

        UserModel user = CreatorMocksUser.createUserModel(); // id = 1L, dni = "123"
        RestaurantModel restaurant = CreatorMocksRestaurant.createRestaurantModel(); // idOwner = 1L

        // Mocks para validación de dueño
        when(userClientPort.ownerExists(dniOwner)).thenReturn(user);
        when(userClientPort.getUserById(idOwner)).thenReturn(user);
        when(restaurantPersistencePort.findRestaurantById(dish.getRestaurantInfo().getIdRestaurant()))
                .thenReturn(restaurant);

        // Act
        dishUseCase.saveDish(dish, dniOwner);

        // Assert
        assertThat(dish.getDishInfo().getIsActive()).isTrue();
        verify(dishPersistencePort).saveDish(dish);
    }


    @Test
    void userIsNotOwner() {
        // Arrange
        DishModel dish = CreatorDishMocks.buildCompleteDishModel(); // idRestaurant = 1L
        String dniOwner = "incorrect-dni"; // no coincide con el dni del "dueño real"
        Long idOwner = 1L;

        // El dueño real del restaurante tiene dni = "123"
        UserModel actualOwner = UserModel.builder()
                .id(idOwner)
                .dni("123")
                .build();

        // El usuario que hace la petición tiene dni = "incorrect-dni"
        UserModel requester = UserModel.builder()
                .id(idOwner) // mismo ID
                .dni(dniOwner)
                .build();

        RestaurantModel restaurant = CreatorMocksRestaurant.createRestaurantModel();// idOwner = 1L

        when(userClientPort.ownerExists(dniOwner)).thenReturn(requester);
        when(userClientPort.getUserById(idOwner)).thenReturn(actualOwner); // devuelve el dueño real
        when(restaurantPersistencePort.findRestaurantById(dish.getRestaurantInfo().getIdRestaurant()))
                .thenReturn(restaurant);

        // Act & Assert
        assertThatThrownBy(() -> dishUseCase.saveDish(dish, dniOwner))
                .isInstanceOf(InvalidUserException.class);

        verify(dishPersistencePort, never()).saveDish(any());
    }

    @Test
    void updateDish() {
        // Arrange
        DishModel dish = CreatorDishMocks.buildCompleteDishModel();
        DishUpdateModel updateModel = new DishUpdateModel( 20.0,"Actualizada");
        String dniOwner = "123";


        when(dishPersistencePort.findDishById(1L)).thenReturn(dish);
        when(userClientPort.ownerExists(dniOwner)).thenReturn(CreatorMocksUser.createUserModel());
        when(restaurantPersistencePort.findRestaurantById(1L)).thenReturn(
              CreatorMocksRestaurant.createRestaurantModel()
        );
        when(userClientPort.getUserById(1L)).thenReturn(CreatorMocksUser.createUserModel());
        when(dishPersistencePort.updateDish(dish, updateModel)).thenReturn(dish);

        // Act
        DishModel result = dishUseCase.updateDish(1L, updateModel, dniOwner);

        // Assert
        assertThat(result.getDishInfo().getDescription()).isEqualTo("Actualizada");
        assertThat(result.getDishInfo().getPrice()).isEqualTo(20.0);

        verify(dishPersistencePort).updateDish(dish, updateModel);
    }

    @Test
    void dishNotFoundOnUpdate() {
        when(dishPersistencePort.findDishById(1L)).thenReturn(null);

        assertThatThrownBy(() -> dishUseCase.updateDish(1L, new DishUpdateModel( 10.0, "desc"), "123"))
                .isInstanceOf(DishNotFoundException.class);
    }

    @Test
    void disableDish() {
        // Arrange
        DishModel dish = CreatorDishMocks.buildCompleteDishModel(); // idRestaurant = 1L
        dish.getDishInfo().setIsActive(true);
        String dniOwner = "123";

        UserModel user = CreatorMocksUser.createUserModel(); // id = 1L, dni = "123"
        RestaurantModel restaurant = CreatorMocksRestaurant.createRestaurantModel(); // idOwner = 1L

        when(dishPersistencePort.findDishById(1L)).thenReturn(dish);
        when(userClientPort.ownerExists(dniOwner)).thenReturn(user);
        when(restaurantPersistencePort.findRestaurantById(1L)).thenReturn(restaurant);
        when(dishPersistencePort.disableDish(dish, false)).thenReturn(dish);

        // Act
        DishModel result = dishUseCase.disableDish(1L, false, dniOwner);

        // Assert
        assertThat(result.getDishInfo().getIsActive()).isFalse();
        verify(dishPersistencePort).disableDish(dish, false);
    }

    @Test
    void dishNotFoundException() {
        when(dishPersistencePort.findDishById(1L)).thenReturn(null);

        assertThatThrownBy(() -> dishUseCase.disableDish(1L, false, "123"))
                .isInstanceOf(DishNotFoundException.class);
    }

    @Test
    void userNotFoundExceptionOnDisable() {
        DishModel dish = CreatorDishMocks.buildCompleteDishModel();
        when(dishPersistencePort.findDishById(1L)).thenReturn(dish);
        when(userClientPort.ownerExists("123")).thenReturn(null);

        assertThatThrownBy(() -> dishUseCase.disableDish(1L, false, "123"))
                .isInstanceOf(UserNotFoundException.class);
    }

    @Test
    void restaurantNotFoundExceptionOnDisable() {
        DishModel dish = CreatorDishMocks.buildCompleteDishModel();
        UserModel user = CreatorMocksUser.createUserModel();

        when(dishPersistencePort.findDishById(1L)).thenReturn(dish);
        when(userClientPort.ownerExists("123")).thenReturn(user);
        when(restaurantPersistencePort.findRestaurantById(1L)).thenReturn(null);

        assertThatThrownBy(() -> dishUseCase.disableDish(1L, false, "123"))
                .isInstanceOf(RestaurantNotFoundException.class);
    }

    @Test
    void invalidUserExceptionDoesNotOwnRestaurant() {
        DishModel dish = CreatorDishMocks.buildCompleteDishModel();

        UserModel user = UserModel.builder()
                .id(99L) // ID incorrecto
                .dni("123")
                .build();

        RestaurantModel restaurant = RestaurantModel.builder()
                .id(1L)
                .idOwner(1L) // Dueño real
                .build();

        when(dishPersistencePort.findDishById(1L)).thenReturn(dish);
        when(userClientPort.ownerExists("123")).thenReturn(user);
        when(restaurantPersistencePort.findRestaurantById(1L)).thenReturn(restaurant);

        assertThatThrownBy(() -> dishUseCase.disableDish(1L, false, "123"))
                .isInstanceOf(InvalidUserException.class);
    }

    @Test
    void getDishesByCategory() {
        List<ListDishesByRestaurantModel> dishes = List.of(CreatorMocksRestaurant.createListDishesByRestaurantModel());
        when(dishPersistencePort.getDishesByCategory(10L, 5L, 0, 10)).thenReturn(dishes);

        List<ListDishesByRestaurantModel> result = dishUseCase.getDishesByCategory(10L, 5L, 0, 10);

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("Test Dish");
    }
}