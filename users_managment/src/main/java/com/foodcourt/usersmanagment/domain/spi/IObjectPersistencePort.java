package com.foodcourt.usersmanagment.domain.spi;

import com.foodcourt.usersmanagment.domain.model.ObjectModel;
import java.util.List;

public interface IObjectPersistencePort {
    ObjectModel saveObject(ObjectModel objectModel);

    List<ObjectModel> getAllObjects();
}