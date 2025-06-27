package com.foodcourt.squaremallmanagment.domain.usecase;

import com.foodcourt.squaremallmanagment.domain.api.IDishServicePort;
import com.foodcourt.squaremallmanagment.domain.exception.*;
import com.foodcourt.squaremallmanagment.domain.model.UserModel;
import com.foodcourt.squaremallmanagment.domain.model.dish.DishModel;
import com.foodcourt.squaremallmanagment.domain.model.dish.DishUpdateModel;
import com.foodcourt.squaremallmanagment.domain.model.dish.ListDishesByRestaurantModel;
import com.foodcourt.squaremallmanagment.domain.model.restaurant.RestaurantModel;
import com.foodcourt.squaremallmanagment.domain.spi.IDishPersistencePort;
import com.foodcourt.squaremallmanagment.domain.spi.IRestaurantPersistencePort;
import com.foodcourt.squaremallmanagment.domain.spi.IUserClientPort;
import com.foodcourt.squaremallmanagment.domain.usecase.util.DishValidationUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
public class DishUseCase implements IDishServicePort {

    private final IDishPersistencePort dishPersistencePort;
    private final IUserClientPort userClientPort;
    private final IRestaurantPersistencePort restaurantPersistencePort;


    @Override
    public void saveDish(DishModel dishModel, String dniOwner) {
        DishValidationUtil.isValidDish(dishModel.getDishInfo().getName(), dishModel.getDishInfo().getPrice());
        validateOwnerRestaurant(dishModel.getRestaurantInfo().getIdRestaurant(), dniOwner);
        dishModel.getDishInfo().setIsActive(Boolean.TRUE);
        dishPersistencePort.saveDish(dishModel);
    }

    @Override
    public DishModel updateDish(Long id, DishUpdateModel dishUpdateModel, String dniOwner) {
        DishModel dish = dishPersistencePort.findDishById(id);

        Optional.ofNullable(dish)
                .orElseThrow(DishNotFoundException::new);

        DishValidationUtil.isValidDish(dishUpdateModel.getDescription(), dishUpdateModel.getPrice());

        validateOwnerRestaurant(dish.getRestaurantInfo().getIdRestaurant(), dniOwner);
        dish.getDishInfo().setDescription(dishUpdateModel.getDescription());
        dish.getDishInfo().setPrice(dishUpdateModel.getPrice());
        return dishPersistencePort.updateDish(dish,dishUpdateModel);
    }

    @Override
    public DishModel disableDish(Long id, Boolean status, String dniOwner) {
    /*
        //DishModel dishToChange = dishPersistencePort.findDishById(id);

        DishModel dishToChange =Optional.ofNullable(dishPersistencePort.findDishById(id))
                .orElseThrow(DishNotFoundException::new);

        Long idDishRestaurantReturned = dishToChange.getRestaurantInfo().getIdRestaurant();

        //Long idUser = userClientPort.ownerExists(dniOwner).getId();

        Long idUser = Optional.ofNullable(userClientPort.ownerExists(dniOwner).getId())
                .orElseThrow(UserNotFoundException::new);

        //Long idOwnerRestaurantReturned = restaurantPersistencePort.findRestaurantById(idDishRestaurantReturned).getIdOwner();

        Long idOwnerRestaurantReturned = Optional.ofNullable(restaurantPersistencePort.findRestaurantById(idDishRestaurantReturned).getIdOwner())
                .orElseThrow(RestaurantNotFoundException::new);

        if (!idOwnerRestaurantReturned.equals(idUser))
            throw new InvalidUserException();

        dishToChange.getDishInfo().setIsActive(status);
        return dishPersistencePort.disableDish(dishToChange, status);*/

        DishModel dishToChange = Optional.ofNullable(dishPersistencePort.findDishById(id))
                .orElseThrow(DishNotFoundException::new);

        Long idDishRestaurantReturned = dishToChange.getRestaurantInfo().getIdRestaurant();

        UserModel user = Optional.ofNullable(userClientPort.ownerExists(dniOwner))
                .orElseThrow(UserNotFoundException::new);

        RestaurantModel restaurant = Optional.ofNullable(restaurantPersistencePort.findRestaurantById(idDishRestaurantReturned))
                .orElseThrow(RestaurantNotFoundException::new);

        if (!user.getId().equals(restaurant.getIdOwner()))
            throw new InvalidUserException();

        dishToChange.getDishInfo().setIsActive(status);
        return dishPersistencePort.disableDish(dishToChange, status);
    }

    @Override
    public List<ListDishesByRestaurantModel>
    getDishesByCategory(Long idRestaurant, Long idCategory, Integer page, Integer size) {
        return dishPersistencePort.getDishesByCategory(idRestaurant, idCategory, page, size);
    }

    private void validateOwner(String dniOwner) {
        Optional.ofNullable(userClientPort.ownerExists(dniOwner))
                .orElseThrow(InvalidUserException::new);
    }

    private void validateOwnerRestaurant(Long idRestaurant, String dniOwner) {
        Long idOwner = restaurantPersistencePort.findRestaurantById(idRestaurant).getIdOwner();
        if (idOwner == null || !userClientPort.getUserById(idOwner).getDni().equals(dniOwner)) {
            throw new InvalidUserException();
        }
    }
}
