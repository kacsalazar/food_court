package com.foodcourt.usersmanagment.infrastructure.out.jpa.adapter;

import com.foodcourt.usersmanagment.domain.exception.ConstantException;
import com.foodcourt.usersmanagment.domain.exception.DomainException;
import com.foodcourt.usersmanagment.domain.model.CreateUserModel;
import com.foodcourt.usersmanagment.domain.model.RolModel;
import com.foodcourt.usersmanagment.domain.model.UserModel;
import com.foodcourt.usersmanagment.domain.spi.IUserPersistencePort;
import com.foodcourt.usersmanagment.infrastructure.out.jpa.entity.UserEntity;
import com.foodcourt.usersmanagment.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.foodcourt.usersmanagment.infrastructure.out.jpa.repository.IUserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class UserAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;
    private final RolAdapter rolAdapter;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(CreateUserModel user) {

        UserEntity userEntity = userEntityMapper.toUserEntity(user);
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(userEntity);
    }

    @Override
    public UserModel findUserById(Long id) {
        return userEntityMapper.toUserModel(userRepository.findById(id).orElse(null));
    }

    @Override
    public UserModel findUserByEmail(String email) {
        UserEntity userEntity = userRepository.findUserByEmail(email);
        return userEntityMapper.toUserModel(userEntity);
    }

    @Override
    public UserModel findUserByDni(String dni) {
        return userEntityMapper.toUserModel(userRepository.findUserByDni(dni));
    }

    public List<UserModel> findEmployeeByRestaurantId(Long restaurantId) {
        List<UserEntity> userEntities = userRepository.findEmployeeByRestaurantId(restaurantId);
        return userEntities.stream().map(userEntityMapper::toUserModel).toList();
    }

}
