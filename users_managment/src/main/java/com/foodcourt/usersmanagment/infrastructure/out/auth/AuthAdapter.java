package com.foodcourt.usersmanagment.infrastructure.out.auth;

import com.foodcourt.usersmanagment.application.handler.ITokenValidator;
import com.foodcourt.usersmanagment.domain.exception.ConstantException;
import com.foodcourt.usersmanagment.domain.exception.DomainException;
import com.foodcourt.usersmanagment.domain.model.AuthModel;
import com.foodcourt.usersmanagment.domain.model.ClaimUserModel;
import com.foodcourt.usersmanagment.domain.model.TokenModel;
import com.foodcourt.usersmanagment.domain.spi.IAuthPort;
import com.foodcourt.usersmanagment.infrastructure.out.jpa.adapter.RolAdapter;
import com.foodcourt.usersmanagment.infrastructure.out.jpa.entity.UserEntity;
import com.foodcourt.usersmanagment.infrastructure.out.jpa.repository.IUserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
@Slf4j
public class AuthAdapter implements IAuthPort {

    private final PasswordEncoder passwordEncoder;
    private final IUserRepository userRepository;
    private final ITokenValidator iTokenValidator;
    private final RolAdapter rolAdapter;

    @Override
    public TokenModel userLogin(AuthModel authModel) {
        UserEntity user = findUserByEmail(authModel.getEmail());

        String rolName = rolAdapter.findById(user.getIdRol()).getName();
        if (rolName == null)
            throw new DomainException(ConstantException.ROLE_NOT_FOUND);

        boolean validPassword = passwordEncoder.matches(authModel.getPassword(), user.getPassword());
        if (!validPassword)
             throw new DomainException(ConstantException.INVALID_PASSWORD);

        ClaimUserModel claimUserModel = new ClaimUserModel(
                new ClaimUserModel.Identity(user.getEmail(), user.getName(),user.getDni()),
                new ClaimUserModel.Authorization(
                        user.getIdRol(),
                       rolName
                ), 1L
        );

        String token = iTokenValidator.generateToken(claimUserModel);

        return TokenModel.builder().token(token).build();

    }

    private UserEntity findUserByEmail(String email) {
        return Optional.ofNullable(userRepository.findUserByEmail(email))
                .orElseThrow(() -> new DomainException(ConstantException.USER_NOT_FOUND));
    }

}
