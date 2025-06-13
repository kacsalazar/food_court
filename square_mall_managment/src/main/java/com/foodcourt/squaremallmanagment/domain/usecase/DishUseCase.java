package com.foodcourt.squaremallmanagment.domain.usecase;

import com.foodcourt.squaremallmanagment.application.handler.util.UtilClass;
import com.foodcourt.squaremallmanagment.domain.api.IDishServicePort;
import com.foodcourt.squaremallmanagment.domain.model.DishModel;
import com.foodcourt.squaremallmanagment.domain.model.DishUpdateModel;
import com.foodcourt.squaremallmanagment.domain.model.RestaurantModel;
import com.foodcourt.squaremallmanagment.domain.model.UserModel;
import com.foodcourt.squaremallmanagment.domain.spi.IDishPersistencePort;
import com.foodcourt.squaremallmanagment.domain.spi.IRestaurantPersistencePort;
import com.foodcourt.squaremallmanagment.domain.spi.IUserClientPort;
import com.foodcourt.squaremallmanagment.domain.usecase.util.DishValidationUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class DishUseCase implements IDishServicePort {

    private final IDishPersistencePort dishPersistencePort;
    private final IUserClientPort userClientPort;
    private final IRestaurantPersistencePort restaurantPersistencePort;


    @Override
    public void saveDish(DishModel dishModel) {
        DishValidationUtil.isValidDish(dishModel);

        if (userClientPort.ownerExists(dishModel.getOwnerInfo().getDniOwner()) == null) {
            throw new IllegalArgumentException("User is not allowed.");
        }
        dishPersistencePort.saveDish(dishModel);
    }

    @Override
    public DishModel updateDish(Long id, DishUpdateModel dishUpdateModel) {
        return dishPersistencePort.updateDish(id, dishUpdateModel);
    }

    @Override
    public DishModel disableDish(Long id, Boolean status, String dniOwner) {

        Long idRestaurant = dishPersistencePort.findDishById(id).getRestaurantInfo().getIdRestaurant();
        Long idUser = userClientPort.ownerExists(dniOwner).getId();
        Long idOwnerRestaurantReturned = restaurantPersistencePort.findRestaurantById(idRestaurant).getIdOwner();

        if (!idOwnerRestaurantReturned.equals(idUser)) {
            throw new IllegalArgumentException("User is not allowed to disable this dish.");
        }
        return dishPersistencePort.disableDish(id, status);
    }
}
