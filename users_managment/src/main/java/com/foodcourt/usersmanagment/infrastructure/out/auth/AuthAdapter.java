package com.foodcourt.usersmanagment.infrastructure.out.auth;

import com.foodcourt.usersmanagment.domain.model.AuthModel;
import com.foodcourt.usersmanagment.domain.model.ClaimUserModel;
import com.foodcourt.usersmanagment.domain.model.TokenModel;
import com.foodcourt.usersmanagment.domain.spi.IAuthPort;
import com.foodcourt.usersmanagment.infrastructure.out.jpa.adapter.RolAdapter;
import com.foodcourt.usersmanagment.infrastructure.out.jpa.entity.UserEntity;
import com.foodcourt.usersmanagment.infrastructure.out.jpa.repository.IUserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.el.parser.Token;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class AuthAdapter implements IAuthPort {

    private final PasswordEncoder passwordEncoder;
    private final IUserRepository userRepository;
    private final JwtService jwtService;
    private final RolAdapter rolAdapter;

    @Override
    public TokenModel userLogin(AuthModel authModel) {
        UserEntity user = this.findUserByEmail(authModel.getEmail());
        boolean validPassword = passwordEncoder.matches(authModel.getPassword(), user.getPassword());
        if (!validPassword) {
             throw new RuntimeException("Invalid password");
        }

        String token = jwtService.generateToken(ClaimUserModel.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .roleName(rolAdapter.findById(user.getIdRol()).getName())
                .idRole(user.getIdRol())
                .build());

        return TokenModel.builder().token(token).build();


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
