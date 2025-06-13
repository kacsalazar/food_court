package com.foodcourt.squaremallmanagment.infrastructure.out.jpa.adapter;

import com.foodcourt.squaremallmanagment.domain.model.DishModel;
import com.foodcourt.squaremallmanagment.domain.model.DishUpdateModel;
import com.foodcourt.squaremallmanagment.domain.spi.IDishPersistencePort;
import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.entity.DishEntity;
import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.mapper.IDishEntityMapper;
import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.mapper.impl.DishEntityMapperData;
import com.foodcourt.squaremallmanagment.infrastructure.out.jpa.repository.IDishRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class DishAdapter implements IDishPersistencePort {

    private final IDishRepository dishRepository;
    private final IDishEntityMapper dishMapper;

    @Override
    public void saveDish(DishModel dishModel) {

        DishEntity dishEntity = DishEntityMapperData.toDishEntity(dishModel);
        dishEntity.setIsActive(Boolean.TRUE);
        dishRepository.save(dishEntity);
    }

    @Override
    public DishModel findDishById(Long id) {
        return DishEntityMapperData.toDishModel(
                dishRepository.findById(id).get()
                        //.orElseThrow(() -> new IllegalArgumentException("Dish with id " + id + " not found."))
        );
    }

    public DishModel updateDish(Long id, DishUpdateModel dishUpdateModel) {
        DishEntity dishEntity = dishRepository.findById(id).get();
        dishEntity.setDescription(dishUpdateModel.getDescription());
        dishEntity.setPrice(dishUpdateModel.getPrice());
        return DishEntityMapperData.toDishModel(dishRepository.save(dishEntity));
    }

    @Override
    public DishModel disableDish(Long id, Boolean status) {
        DishEntity dishEntity = dishRepository.findById(id).get();
        dishEntity.setIsActive(status);
        return DishEntityMapperData.toDishModel(dishRepository.save(dishEntity));
    }
}
