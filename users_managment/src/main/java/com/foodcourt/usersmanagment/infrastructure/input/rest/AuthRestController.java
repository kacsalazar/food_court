package com.foodcourt.usersmanagment.infrastructure.input.rest;

import com.foodcourt.usersmanagment.application.dto.request.AuthRequestDto;
import com.foodcourt.usersmanagment.application.handler.IAuthHandler;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/user/auth")
public class AuthRestController {

    private final IAuthHandler authHandler;

    @PostMapping("/")
    public ResponseEntity<Void> userLogin(@RequestBody AuthRequestDto authRequestDto){
        authHandler.userLogin(authRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
