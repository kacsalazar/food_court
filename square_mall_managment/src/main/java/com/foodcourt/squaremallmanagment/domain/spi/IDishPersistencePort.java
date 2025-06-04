package com.foodcourt.squaremallmanagment.domain.spi;

import com.foodcourt.squaremallmanagment.domain.model.DishModel;

public interface IDishPersistencePort {

    void saveDish(DishModel dishModel);

}
