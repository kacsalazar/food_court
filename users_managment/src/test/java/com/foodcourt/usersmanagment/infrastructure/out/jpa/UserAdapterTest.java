package com.foodcourt.usersmanagment.infrastructure.out.jpa;

import com.foodcourt.usersmanagment.domain.model.CreateUserModel;
import com.foodcourt.usersmanagment.CreatorMocks;
import com.foodcourt.usersmanagment.domain.model.RolModel;
import com.foodcourt.usersmanagment.domain.model.UserModel;
import com.foodcourt.usersmanagment.infrastructure.out.jpa.adapter.RolAdapter;
import com.foodcourt.usersmanagment.infrastructure.out.jpa.adapter.UserAdapter;
import com.foodcourt.usersmanagment.infrastructure.out.jpa.entity.UserEntity;
import com.foodcourt.usersmanagment.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.foodcourt.usersmanagment.infrastructure.out.jpa.repository.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class UserAdapterTest {

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private IUserRepository userRepository;

    @Mock
    private IUserEntityMapper userEntityMapper;

    @Mock
    private RolAdapter rolAdapter;

    @InjectMocks
    private UserAdapter userAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(passwordEncoder.encode("plainPassword")).thenReturn("encodedPassword");
    }

    @Test
    void testSaveUser() {
        // Given
        CreateUserModel createUserModel = new CreateUserModel();
        UserEntity userEntity = new UserEntity();
        RolModel rolModel = new RolModel();
        rolModel.setId(1L);

        when(userEntityMapper.toUserEntity(createUserModel)).thenReturn(userEntity);
        when(rolAdapter.findByName("ROLE_OWNER")).thenReturn(rolModel);
        when(userRepository.save(userEntity)).thenReturn(userEntity);
        when(userEntityMapper.toOwnerModel(userEntity)).thenReturn(createUserModel);

        // When
        userAdapter.saveUser(createUserModel);

        // Then
        verify(userEntityMapper, times(1)).toUserEntity(createUserModel);
        verify(rolAdapter, times(1)).findByName("ROLE_OWNER");
        verify(userRepository, times(1)).save(userEntity);
        verify(userEntityMapper, times(1)).toOwnerModel(userEntity);
        //assertEquals(saveUserModel, result);
    }

    @Test
    void testFindUserById() {
        // Given
        Long id = 1L;
        UserEntity userEntity = new UserEntity();
        UserModel userModel = new UserModel();

        when(userRepository.findById(id)).thenReturn(java.util.Optional.of(userEntity));
        when(userEntityMapper.toUserModel(userEntity)).thenReturn(userModel);

        // When
        UserModel result = userAdapter.findUserById(id);

        // Then
        verify(userRepository, times(1)).findById(id);
        verify(userEntityMapper, times(1)).toUserModel(userEntity);
        assertEquals(userModel, result);
    }

    @Test
    void testVerifyUserRol() {
        // Given
        String id = "1L";
        String role = "ROLE_ADMIN";
        UserEntity userEntity = CreatorMocks.createUserEntity();
        userEntity.setIdRol(1L);
        RolModel rolModel = CreatorMocks.createRolModel()
                ;
        rolModel.setId(1L);

        when(userRepository.findUserByDni(id)).thenReturn(userEntity);
        when(rolAdapter.findByName(role)).thenReturn(rolModel);

        // When
        //Boolean result = userAdapter.verifyUserRol(id, role);

        // Then
        verify(userRepository, times(1)).findUserByDni(id);
        verify(rolAdapter, times(1)).findByName(role);
        //assertTrue(result);
    }

    @Test
    void testFindUserByEmail_UserFound() {
        // Arrange
        String email = "test@mail.com";
        UserEntity userEntity = new UserEntity();
        UserModel userModel = new UserModel();

        when(userRepository.findUserByEmail(email)).thenReturn(userEntity);
        when(userEntityMapper.toUserModel(userEntity)).thenReturn(userModel);

        // Act
        UserModel result = userAdapter.findUserByEmail(email);

        // Assert
        assertEquals(userModel, result);
        verify(userRepository).findUserByEmail(email);
        verify(userEntityMapper).toUserModel(userEntity);
    }

    @Test
    void testFindUserByEmail_UserNotFound() {
        // Arrange
        String email = "notfound@mail.com";
        when(userRepository.findUserByEmail(email)).thenReturn(null);

        // Act
        UserModel result = userAdapter.findUserByEmail(email);

        // Assert
        assertEquals(null, result);
        verify(userRepository).findUserByEmail(email);
        verify(userEntityMapper, never()).toUserModel(any());
    }

    @Test
    void testFindUserByDni() {
        // Arrange
        String dni = "123456";
        UserEntity userEntity = new UserEntity();
        UserModel userModel = new UserModel();

        when(userRepository.findUserByDni(dni)).thenReturn(userEntity);
        when(userEntityMapper.toUserModel(userEntity)).thenReturn(userModel);

        // Act
        UserModel result = userAdapter.findUserByDni(dni);

        // Assert
        assertEquals(userModel, result);
        verify(userRepository).findUserByDni(dni);
        verify(userEntityMapper).toUserModel(userEntity);
    }

    @Test
    void testCreateAccountEmployee() {
        CreateUserModel createUserModel = new CreateUserModel();
        createUserModel.setPassword("plainPassword");
        UserEntity userEntity = new UserEntity();
        RolModel rolModel = new RolModel();
        rolModel.setId(2L);

        when(userEntityMapper.toUserEntity(createUserModel)).thenReturn(userEntity);
        when(rolAdapter.findByName("ROLE_EMPLOYEE")).thenReturn(rolModel);
        when(passwordEncoder.encode("plainPassword")).thenReturn("encodedPassword");

        //userAdapter.createAccountEmployee(createUserModel);

        verify(userEntityMapper).toUserEntity(createUserModel);
        verify(rolAdapter).findByName("ROLE_EMPLOYEE");
        verify(passwordEncoder).encode("plainPassword");
        verify(userRepository).save(userEntity);
        // Verifica que los valores se asignaron correctamente
        assert userEntity.getIdRol().equals(2L);
        assert userEntity.getPassword().equals("encodedPassword");
    }

    @Test
    void testCreateAccountCustomer() {
        CreateUserModel createUserModel = new CreateUserModel();
        createUserModel.setPassword("plainPassword");
        UserEntity userEntity = new UserEntity();
        RolModel rolModel = new RolModel();
        rolModel.setId(3L);

        when(userEntityMapper.toUserEntity(createUserModel)).thenReturn(userEntity);
        when(rolAdapter.findByName("ROLE_CUSTOMER")).thenReturn(rolModel);
        when(passwordEncoder.encode("plainPassword")).thenReturn("encodedPassword");

        //userAdapter.createAccountCustomer(createUserModel);

        verify(userEntityMapper).toUserEntity(createUserModel);
        verify(rolAdapter).findByName("ROLE_CUSTOMER");
        verify(passwordEncoder).encode("plainPassword");
        verify(userRepository).save(userEntity);
        assert userEntity.getIdRol().equals(3L);
        assert userEntity.getPassword().equals("encodedPassword");
    }
}
