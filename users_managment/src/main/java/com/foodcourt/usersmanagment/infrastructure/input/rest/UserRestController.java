package com.foodcourt.usersmanagment.infrastructure.input.rest;

import com.foodcourt.usersmanagment.application.dto.request.OwnerRequestDto;
import com.foodcourt.usersmanagment.application.dto.response.UserResponseDto;
import com.foodcourt.usersmanagment.application.handler.IUserHandler;
import com.foodcourt.usersmanagment.infrastructure.documentation.IUserRestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserRestController implements IUserRestController {

    private final IUserHandler userHandler;

    @PostMapping("/")
    public ResponseEntity<Void> saveUser(@RequestBody OwnerRequestDto ownerRequestDto) {
        userHandler.saveUser(ownerRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        UserResponseDto userResponseDto = userHandler.getUserById(id);
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }


    @GetMapping("/verify/{id}/{rol}")
    public ResponseEntity<Boolean> verifyUserRol(@PathVariable String dni, @PathVariable String rol) {
        boolean isVerified = userHandler.verifyUserRol(dni, rol);
        return new ResponseEntity<>(isVerified, HttpStatus.OK);
    }
}
