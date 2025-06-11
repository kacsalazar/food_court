package com.foodcourt.usersmanagment.infrastructure.out.jpa.adapter;

import com.foodcourt.usersmanagment.domain.model.OwnerModel;
import com.foodcourt.usersmanagment.domain.model.RolModel;
import com.foodcourt.usersmanagment.domain.model.UserModel;
import com.foodcourt.usersmanagment.domain.spi.IUserPersistencePort;
import com.foodcourt.usersmanagment.infrastructure.out.jpa.entity.RolEntity;
import com.foodcourt.usersmanagment.infrastructure.out.jpa.entity.UserEntity;
import com.foodcourt.usersmanagment.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.foodcourt.usersmanagment.infrastructure.out.jpa.repository.IUserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class UserAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;
    private final RolAdapter rolAdapter;

    @Override
    public OwnerModel saveOwner(OwnerModel ownerModel) {

        UserEntity userEntity = userEntityMapper.toUserEntity(ownerModel);
        userEntity.setIdRol(rolAdapter.findByName("ROLE_OWNER").getId());
        userRepository.save(userEntity);
        log.info("User saved: {}" + userEntity);
        return userEntityMapper.toOwnerModel(userEntity);
    }

    @Override
    public UserModel findUserById(Long id) {
        log.info("Finding owner by id: {}", id);
        return userEntityMapper.toUserModel(userRepository.findById(id).orElse(null));
    }

    @Override
    public Boolean verifyUserRol(String dni, String role) {

        UserEntity user = userRepository.findUserByDni(dni);
        RolModel rol = rolAdapter.findByName(role);

        return user.getIdRol().equals(rol.getId());
    }

    @Override
    public UserModel findUserByEmail(String email) {
        log.info("Finding user by email: {}", email);
        UserEntity userEntity = userRepository.findUserByEmail(email);
        if (userEntity == null) {
            return null;
        }
        return userEntityMapper.toUserModel(userEntity);
    }

    @Override
    public UserModel findUserByDni(String dni) {
        return userEntityMapper.toUserModel(userRepository.findUserByDni(dni));
    }


}
