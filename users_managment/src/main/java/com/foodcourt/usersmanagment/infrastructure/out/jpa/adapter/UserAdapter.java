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
    public Boolean verifyUserRol(Long id, String role) {

        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        RolModel rol = rolAdapter.findByName(role);

        if (user.getIdRol() == rol.getId()) return true;
        return false;
    }


}
