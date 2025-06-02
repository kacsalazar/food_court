package com.foodcourt.usersmanagment.infraestructure.out.jpa;

import com.foodcourt.usersmanagment.domain.model.OwnerModel;
import com.foodcourt.usersmanagment.CreatorMocks;
import com.foodcourt.usersmanagment.infrastructure.out.jpa.adapter.UserAdapter;
import com.foodcourt.usersmanagment.infrastructure.out.jpa.entity.UserEntity;
import com.foodcourt.usersmanagment.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.foodcourt.usersmanagment.infrastructure.out.jpa.repository.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserAdapterTest {

    @Mock
    IUserRepository userRepository;

    @Mock
    IUserEntityMapper userEntityMapper;

    @InjectMocks
    UserAdapter userAdapter;

    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveOwner() {

        UserEntity userEntity = CreatorMocks.createUserEntity();
        OwnerModel ownerModel = CreatorMocks.createOwnerModel();

        when(userEntityMapper.toUserEntity(ownerModel)).thenReturn(userEntity);
        when(userRepository.save(userEntity)).thenReturn(userEntity);
        when(userEntityMapper.toOwnerModel(userEntity)).thenReturn(ownerModel);

        OwnerModel model = userAdapter.saveOwner(ownerModel);

        assertEquals(ownerModel, model);
        verify(userEntityMapper, times(1)).toUserEntity(ownerModel);
        verify(userEntityMapper, times(1)).toOwnerModel(userEntity);
        verify(userRepository, times(1)).save(userEntity);
    }
}
