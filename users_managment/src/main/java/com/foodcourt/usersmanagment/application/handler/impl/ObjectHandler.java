package com.foodcourt.usersmanagment.application.handler.impl;

import com.foodcourt.usersmanagment.application.dto.request.ObjectRequestDto;
import com.foodcourt.usersmanagment.application.dto.response.ObjectResponseDto;
import com.foodcourt.usersmanagment.application.handler.IObjectHandler;
import com.foodcourt.usersmanagment.application.mapper.IObjectRequestMapper;
import com.foodcourt.usersmanagment.application.mapper.IObjectResponseMapper;
import com.foodcourt.usersmanagment.domain.api.IObjectServicePort;
import com.foodcourt.usersmanagment.domain.model.ObjectModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ObjectHandler implements IObjectHandler {

    private final IObjectServicePort objectServicePort;
    private final IObjectRequestMapper objectRequestMapper;
    private final IObjectResponseMapper objectResponseMapper;

    @Override
    public void saveObject(ObjectRequestDto objectRequestDto) {
        ObjectModel objectModel = objectRequestMapper.toObject(objectRequestDto);
        objectServicePort.saveObject(objectModel);
    }

    @Override
    public List<ObjectResponseDto> getAllObjects() {
        return objectResponseMapper.toResponseList(objectServicePort.getAllObjects());
    }
}