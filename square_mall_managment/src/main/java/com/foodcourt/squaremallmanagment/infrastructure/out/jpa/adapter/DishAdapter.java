package com.foodcourt.squaremallmanagment.infrastructure.out.jpa.adapter;

import com.foodcourt.squaremallmanagment.domain.model.dish.DishModel;
import com.foodcourt.squaremallmanagment.domain.model.dish.DishUpdateModel;
import com.foodcourt.squaremallmanagment.domain.model.dish.ListDishesByRestaurantModel;
import com.foodcourt.squaremallmanagment.domain.spi.IDishPersistencePort;
import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.entity.DishEntity;
import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.mapper.IDishEntityMapper;
import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.mapper.impl.DishEntityMapperData;
import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.repository.IDishRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class DishAdapter implements IDishPersistencePort {

    private final IDishRepository dishRepository;
    private final IDishEntityMapper dishMapper;

    @Override
    public void saveDish(DishModel dishModel) {

        DishEntity dishEntity = DishEntityMapperData.toDishEntity(dishModel);
        dishRepository.save(dishEntity);
    }

    @Override
    public DishModel findDishById(Long id) {
        return DishEntityMapperData.toDishModel(
                dishRepository.findById(id).get()
        );
    }

    public DishModel updateDish(DishModel dish, DishUpdateModel dishUpdateModel) {
        DishEntity dishEntity = dishMapper.toDishEntity(dish);
        return DishEntityMapperData.toDishModel(dishRepository.save(dishEntity));
    }

    @Override
    public DishModel disableDish(DishModel dish, Boolean status) {
        DishEntity dishEntity = dishMapper.toDishEntity(dish);
        return DishEntityMapperData.toDishModel(dishRepository.save(dishEntity));
    }

    @Override
    public List<ListDishesByRestaurantModel> getDishesByCategory(Long idRestaurant, Long idCategory, Integer page, Integer size) {

        return DishEntityMapperData.toDishesByRestaurantModelList(dishRepository
                .findDishesByRestaurant(idRestaurant, idCategory, page, size));
    }
}
