package com.foodcourt.usersmanagment.infrastructure.input.rest;

import com.foodcourt.usersmanagment.application.dto.request.AuthRequestDto;
import com.foodcourt.usersmanagment.application.dto.response.TokenResponseDto;
import com.foodcourt.usersmanagment.application.handler.IAuthHandler;
import com.foodcourt.usersmanagment.infrastructure.documentation.IAuthRestController;
import com.foodcourt.usersmanagment.infrastructure.out.auth.JwtService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user/auth")
public class AuthRestController implements IAuthRestController {

    private final IAuthHandler authHandler;

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> userLogin(@RequestBody AuthRequestDto authRequestDto){
        return new ResponseEntity<>(authHandler.userLogin(authRequestDto), HttpStatus.OK);
    }
}
