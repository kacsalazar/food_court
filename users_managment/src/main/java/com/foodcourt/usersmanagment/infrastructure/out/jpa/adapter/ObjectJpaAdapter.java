package com.foodcourt.usersmanagment.infrastructure.out.jpa.adapter;

import com.foodcourt.usersmanagment.domain.model.ObjectModel;
import com.foodcourt.usersmanagment.domain.spi.IObjectPersistencePort;
import com.foodcourt.usersmanagment.infrastructure.exception.NoDataFoundException;
import com.foodcourt.usersmanagment.infrastructure.out.jpa.mapper.IObjectEntityMapper;
import com.foodcourt.usersmanagment.infrastructure.out.jpa.repository.IObjectRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ObjectJpaAdapter implements IObjectPersistencePort {

    //private final IObjectRepository objectRepository;
    //private final IObjectEntityMapper objectEntityMapper;


    @Override
    public ObjectModel saveObject(ObjectModel objectModel) {
       /* ObjectEntity objectEntity = objectRepository.save(objectEntityMapper.toEntity(objectModel));
        return objectEntityMapper.toObjectModel(objectEntity);*/
        return null;
    }

    @Override
    public List<ObjectModel> getAllObjects() {
        /*List<ObjectEntity> entityList = objectRepository.findAll();
        if (entityList.isEmpty()) {
            throw new NoDataFoundException();
        }
        return objectEntityMapper.toObjectModelList(entityList);*/
        return null;
    }
}