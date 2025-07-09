package com.foodcourt.squaremallmanagment.domain.usecase;

import com.foodcourt.squaremallmanagment.domain.api.IDishServicePort;
import com.foodcourt.squaremallmanagment.domain.exception.*;
import com.foodcourt.squaremallmanagment.domain.model.UserModel;
import com.foodcourt.squaremallmanagment.domain.model.dish.DishModel;
import com.foodcourt.squaremallmanagment.domain.model.dish.DishUpdateModel;
import com.foodcourt.squaremallmanagment.domain.model.dish.ListDishesByRestaurantModel;
import com.foodcourt.squaremallmanagment.domain.model.dish.ListDishesRetrieved;
import com.foodcourt.squaremallmanagment.domain.model.restaurant.RestaurantModel;
import com.foodcourt.squaremallmanagment.domain.spi.IDishPersistencePort;
import com.foodcourt.squaremallmanagment.domain.spi.IRestaurantPersistencePort;
import com.foodcourt.squaremallmanagment.domain.spi.IUserRestPort;
import com.foodcourt.squaremallmanagment.domain.usecase.util.DishValidationUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
public class DishUseCase implements IDishServicePort {

    private final IDishPersistencePort dishPersistencePort;
    private final IUserRestPort userClientPort;
    private final IRestaurantPersistencePort restaurantPersistencePort;


    @Override
    public void saveDish(DishModel dishModel, String dniOwner) {
        DishValidationUtil.isValidDish(dishModel.getDishInfo().getName(), dishModel.getDishInfo().getPrice());
        validateDishRestaurantVsOwnerRestaurant(dishModel.getRestaurantInfo().getIdRestaurant(), dniOwner);
        dishModel.getDishInfo().setIsActive(Boolean.TRUE);
        dishPersistencePort.saveDish(dishModel);
    }

    @Override
    public DishModel updateDish(Long dishId, DishUpdateModel dishUpdateModel, String dniOwner) {
        DishModel dish = dishPersistencePort.findDishById(dishId);

        Optional.ofNullable(dish)
                .orElseThrow(DishNotFoundException::new);

        DishValidationUtil.isValidDish(dishUpdateModel.getDescription(), dishUpdateModel.getPrice());

        validateDishRestaurantVsOwnerRestaurant(dish.getRestaurantInfo().getIdRestaurant(), dniOwner);
        dish.getDishInfo().setDescription(dishUpdateModel.getDescription());
        dish.getDishInfo().setPrice(dishUpdateModel.getPrice());
        return dishPersistencePort.updateDish(dish,dishUpdateModel);
    }

    @Override
    public DishModel disableDish(Long dishId, Boolean newDishStatus, String dniOwner) {

        // Obtener el plato a modificar
        DishModel dishToChange = Optional.ofNullable(dishPersistencePort.findDishById(dishId))
                .orElseThrow(DishNotFoundException::new);

        // Validar que el owner sea el propietario del restaurante del plato.
        validateDishRestaurantVsOwnerRestaurant(dishToChange.getRestaurantInfo().getIdRestaurant(), dniOwner);

        dishToChange.getDishInfo().setIsActive(newDishStatus);
        return dishPersistencePort.disableDish(dishToChange);
    }

    //validar que el owner del restaurante sea el mismo que el que hace la peticion de crear y modificar platos
    private void validateDishRestaurantVsOwnerRestaurant(Long idRestaurant, String dniOwnerRequester) {
        RestaurantModel restaurantOfDish = restaurantPersistencePort.findRestaurantById(idRestaurant);
        if (restaurantOfDish == null) throw new RestaurantNotFoundException();
        UserModel dishRestaurantOwner = userClientPort.getUserById(restaurantOfDish.getIdOwner());

        if ((dishRestaurantOwner != null)){
            if (!dishRestaurantOwner.getDni().equals(dniOwnerRequester))throw new InvalidUserException();
        }else{
            throw new UserNotFoundException();
        }

    }

    @Override
    public List<ListDishesByRestaurantModel>
    getDishesByCategory(ListDishesRetrieved listDishesRetrieved) {
        return dishPersistencePort.getDishesByCategory(listDishesRetrieved);
    }
}
