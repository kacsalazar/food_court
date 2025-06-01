package com.foodcourt.usersmanagment.domain.usecase;

import com.foodcourt.usersmanagment.domain.model.OwnerModel;
import com.foodcourt.usersmanagment.domain.spi.IUserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class UserUseCaseTest {

    @Mock
    IUserPersistencePort userPersistencePort;

    @InjectMocks
    UserUseCase userUseCase;

    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveUser() {
        // Aquí puedes implementar el test para guardar un usuario

        OwnerModel ownerModel = CreatorMocks.createOwnerModel();

        userUseCase.saveOwner(ownerModel);

        verify(userPersistencePort, times(1)).saveOwner(ownerModel);
    }
}
