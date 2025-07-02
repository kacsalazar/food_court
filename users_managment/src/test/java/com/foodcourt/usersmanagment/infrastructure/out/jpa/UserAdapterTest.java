package com.foodcourt.usersmanagment.infrastructure.out.jpa;

import com.foodcourt.usersmanagment.CreatorMocks;
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

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
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
    void saveUserSuccessfully() {
        when(userEntityMapper.toUserEntity(CreatorMocks.createCreateUserModel())).thenReturn(CreatorMocks.createUserEntity());
        when(passwordEncoder.encode(CreatorMocks.createCreateUserModel().getPassword())).thenReturn("securePassword");

        userAdapter.saveUser(CreatorMocks.createCreateUserModel());

        verify(userRepository).save(CreatorMocks.createUserEntity());
        verify(passwordEncoder).encode("securePassword");
    }

    @Test
    void findUserById() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(CreatorMocks.createUserEntity()));
        when(userEntityMapper.toUserModel(CreatorMocks.createUserEntity())).thenReturn(CreatorMocks.createUserModel());

        UserModel result = userAdapter.findUserById(1L);

        assertThat(result).isEqualTo(CreatorMocks.createUserModel());
        verify(userRepository).findById(1L);
    }

    @Test
    void findUserByEmail() {
        when(userRepository.findUserByEmail("john@example.com")).thenReturn(CreatorMocks.createUserEntity());
        when(userEntityMapper.toUserModel(CreatorMocks.createUserEntity())).thenReturn(CreatorMocks.createUserModel());

        UserModel result = userAdapter.findUserByEmail("john@example.com");

        assertThat(result).isEqualTo(CreatorMocks.createUserModel());
        verify(userRepository).findUserByEmail("john@example.com");
    }

    @Test
    void findUserByDni() {
        when(userRepository.findUserByDni("123456789")).thenReturn(CreatorMocks.createUserEntity());
        when(userEntityMapper.toUserModel(CreatorMocks.createUserEntity())).thenReturn(CreatorMocks.createUserModel());

        UserModel result = userAdapter.findUserByDni("123456789");

        assertThat(result).isEqualTo(CreatorMocks.createUserModel());
        verify(userRepository).findUserByDni("123456789");
    }

    @Test
    void findEmployeesByRestaurantId() {
        List<UserEntity> entities = List.of(CreatorMocks.createUserEntity());
        when(userRepository.findEmployeeByRestaurantId(1L)).thenReturn(entities);
        when(userEntityMapper.toUserModel(CreatorMocks.createUserEntity())).thenReturn(CreatorMocks.createUserModel());

        List<UserModel> result = userAdapter.findEmployeeByRestaurantId(1L);

        assertThat(result).hasSize(1);
        assertThat(result.get(0)).isEqualTo(CreatorMocks.createUserModel());
        verify(userRepository).findEmployeeByRestaurantId(1L);
    }
}