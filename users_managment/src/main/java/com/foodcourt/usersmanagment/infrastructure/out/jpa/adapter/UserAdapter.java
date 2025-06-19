package com.foodcourt.usersmanagment.infrastructure.out.jpa.adapter;

import com.foodcourt.usersmanagment.domain.exception.ConstantException;
import com.foodcourt.usersmanagment.domain.exception.DomainException;
import com.foodcourt.usersmanagment.domain.model.SaveUserModel;
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

@Component
@AllArgsConstructor
@Slf4j
public class UserAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;
    private final RolAdapter rolAdapter;
    private final PasswordEncoder passwordEncoder;

    @Override
    public SaveUserModel saveOwner(SaveUserModel saveUserModel) {

        UserEntity userEntity = userEntityMapper.toUserEntity(saveUserModel);
        Long idRol = rolAdapter.findByName("ROLE_OWNER").getId();
        if(idRol == null)
            throw new DomainException(ConstantException.ROLE_NOT_FOUND);

        userEntity.setIdRol(idRol);
        userEntity.setPassword(passwordEncoder.encode(saveUserModel.getPassword()));
        userRepository.save(userEntity);

        return userEntityMapper.toOwnerModel(userEntity);
    }

    @Override
    public UserModel findUserById(Long id) {
        return userEntityMapper.toUserModel(userRepository.findById(id).orElse(null));
    }

    @Override
    public Boolean verifyUserRol(UserModel user, String role) {
        RolModel rol = rolAdapter.findByName(role);
        if (rol == null) throw new DomainException(ConstantException.ROLE_NOT_FOUND);
        return user.getIdRol().equals(rol.getId());
    }

    @Override
    public UserModel findUserByEmail(String email) {
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

    @Override
    public void createAccountEmployee(SaveUserModel saveUserModel) {
        UserEntity userEntity = userEntityMapper.toUserEntity(saveUserModel);
        Long idRol = rolAdapter.findByName("ROLE_EMPLOYEE").getId();
        if(idRol == null)
            throw new DomainException(ConstantException.ROLE_NOT_FOUND);

        userEntity.setIdRol(idRol);
        userEntity.setPassword(passwordEncoder.encode(saveUserModel.getPassword()));
        userRepository.save(userEntity);
    }

    @Override
    public void createAccountCustomer(SaveUserModel saveUserModel) {
        UserEntity userEntity = userEntityMapper.toUserEntity(saveUserModel);
        Long idRol = rolAdapter.findByName("ROLE_CUSTOMER").getId();
        if(idRol == null)
            throw new DomainException(ConstantException.ROLE_NOT_FOUND);
        userEntity.setIdRol(idRol);
        userEntity.setPassword(passwordEncoder.encode(saveUserModel.getPassword()));
        userRepository.save(userEntity);
    }

}
