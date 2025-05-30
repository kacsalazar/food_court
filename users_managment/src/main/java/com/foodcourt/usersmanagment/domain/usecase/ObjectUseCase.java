package com.foodcourt.usersmanagment.domain.usecase;

import com.foodcourt.usersmanagment.domain.api.IObjectServicePort;
import com.foodcourt.usersmanagment.domain.model.ObjectModel;
import com.foodcourt.usersmanagment.domain.spi.IObjectPersistencePort;

import java.util.List;

public class ObjectUseCase implements IObjectServicePort {

    private final IObjectPersistencePort objectPersistencePort;

    public ObjectUseCase(IObjectPersistencePort objectPersistencePort) {
        this.objectPersistencePort = objectPersistencePort;
    }

    @Override
    public void saveObject(ObjectModel objectModel) {
        objectPersistencePort.saveObject(objectModel);
    }

    @Override
    public List<ObjectModel> getAllObjects() {
        return objectPersistencePort.getAllObjects();
    }
}