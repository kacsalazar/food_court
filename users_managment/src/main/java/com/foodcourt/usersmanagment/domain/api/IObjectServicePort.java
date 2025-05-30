package com.foodcourt.usersmanagment.domain.api;

import com.foodcourt.usersmanagment.domain.model.ObjectModel;

import java.util.List;

public interface IObjectServicePort {

    void saveObject(ObjectModel objectModel);

    List<ObjectModel> getAllObjects();
}