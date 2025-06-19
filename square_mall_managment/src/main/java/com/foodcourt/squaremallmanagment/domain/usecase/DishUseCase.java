package com.foodcourt.squaremallmanagment.domain.usecase;

import com.foodcourt.squaremallmanagment.application.handler.util.UtilClass;
import com.foodcourt.squaremallmanagment.domain.api.IDishServicePort;
import com.foodcourt.squaremallmanagment.domain.exception.ConstantException;
import com.foodcourt.squaremallmanagment.domain.exception.DomainException;
import com.foodcourt.squaremallmanagment.domain.model.*;
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
    public void saveDish(DishModel dishModel) {
        DishValidationUtil.isValidDish(dishModel);
        validateOwner(dishModel.getOwnerInfo().getDniOwner());
        dishPersistencePort.saveDish(dishModel);
    }

    @Override
    public DishModel updateDish(Long id, DishUpdateModel dishUpdateModel) {
        DishModel dish = dishPersistencePort.findDishById(id);
        if (dish == null) throw new DomainException(ConstantException.DISH_NOT_FOUND);
        return dishPersistencePort.updateDish(dish,dishUpdateModel);
    }

    @Override
    public DishModel disableDish(Long id, Boolean status, String dniOwner) {

        DishModel dishToChange = dishPersistencePort.findDishById(id);
        if (dishToChange == null)
            throw new DomainException(ConstantException.DISH_NOT_FOUND);

        Long idDishRestaurantReturned = dishToChange.getRestaurantInfo().getIdRestaurant();

        Long idUser = userClientPort.ownerExists(dniOwner).getId();
        if (idUser == null)
            throw new DomainException(ConstantException.USER_NOT_FOUND);

        Long idOwnerRestaurantReturned = restaurantPersistencePort.findRestaurantById(idDishRestaurantReturned).getIdOwner();
        if (idOwnerRestaurantReturned == null)
            throw new DomainException(ConstantException.RESTAURANT_NOT_FOUND);

        if (!idOwnerRestaurantReturned.equals(idUser))
            throw new DomainException(ConstantException.USER_NOT_AUTHORIZED);

        return dishPersistencePort.disableDish(dishToChange, status);
    }

    @Override
    public List<ListDishesByRestaurantModel>
    getDishesByCategory(Long idRestaurant, Long idCategory, Integer page, Integer size) {
        return dishPersistencePort.getDishesByCategory(idRestaurant, idCategory, page, size);
    }

    private void validateOwner(String dniOwner) {
        Optional.ofNullable(userClientPort.ownerExists(dniOwner))
                .orElseThrow(() -> new DomainException(ConstantException.USER_NOT_ALLOWED));
    }
}
