package com.foodcourt.usersmanagment.infrastructure.out.auth;

import com.foodcourt.usersmanagment.domain.model.AuthModel;
import com.foodcourt.usersmanagment.domain.model.UserModel;
import com.foodcourt.usersmanagment.domain.spi.IAuthPort;
import com.foodcourt.usersmanagment.domain.spi.IUserPersistencePort;
import com.foodcourt.usersmanagment.infrastructure.out.jpa.entity.UserEntity;
import com.foodcourt.usersmanagment.infrastructure.out.jpa.repository.IUserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class AuthAdapter implements IAuthPort {

    private final PasswordEncoder passwordEncoder;
    private final IUserRepository userRepository;


    @Override
    public void userLogin(AuthModel authModel) {
        UserEntity user = this.findUserByEmail(authModel.getEmail());

        log.info("User found: {}", user);
        log.info("Password provided: {}", authModel.getPassword());
        boolean validPassword = passwordEncoder.matches(authModel.getPassword(), user.getPassword());
        if (!validPassword) {
             throw new RuntimeException("Invalid password");
        }
    }

    public UserEntity findUserByEmail(String email) {
        log.info("Finding user by email: {}", email);
        UserEntity userEntity = userRepository.findUserByEmail(email);
        if (userEntity == null) {
            return null;
        }
        return userEntity;
    }
}
