package com.foodcourt.usersmanagment.application.handler;

import com.foodcourt.usersmanagment.application.dto.request.ObjectRequestDto;
import com.foodcourt.usersmanagment.application.dto.response.ObjectResponseDto;

import java.util.List;

public interface IObjectHandler {

    void saveObject(ObjectRequestDto objectRequestDto);

    List<ObjectResponseDto> getAllObjects();
}