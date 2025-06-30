package com.foodcourt.usersmanagment.infrastructure.input.rest;

import com.foodcourt.usersmanagment.application.dto.request.OwnerRequestDto;
import com.foodcourt.usersmanagment.application.dto.request.UserRequestDto;
import com.foodcourt.usersmanagment.application.dto.response.UserResponseDto;
import com.foodcourt.usersmanagment.application.handler.IUserHandler;
import com.foodcourt.usersmanagment.infrastructure.documentation.IUserRestController;
import com.foodcourt.usersmanagment.infrastructure.input.rest.util.SecurityExpressions;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserRestController implements IUserRestController {

    private final IUserHandler userHandler;

    @PreAuthorize(SecurityExpressions.ADMIN)
    @PostMapping("/owner/")
    public ResponseEntity<Void> saveUser(@RequestBody UserRequestDto userRequestDto) {
        userHandler.saveUser(userRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        UserResponseDto userResponseDto = userHandler.getUserById(id);
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @GetMapping("/verify/{dni}/{rol}")
    public ResponseEntity<Boolean> verifyUserRol(@PathVariable String dni, @PathVariable String rol) {
        boolean isVerified = userHandler.verifyUserRol(dni, rol);
        return new ResponseEntity<>(isVerified, HttpStatus.OK);
    }

    @PreAuthorize(SecurityExpressions.OWNER)
    @PostMapping("/employee/")
    public ResponseEntity<Void> createAccountEmployee(@RequestBody OwnerRequestDto userRequestDto){
        userHandler.createAccountEmployee(userRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping("/dni/{dni}")
    public ResponseEntity<UserResponseDto> getUserByDni(@PathVariable String dni) {
        UserResponseDto userResponseDto = userHandler.getUserByDni(dni);
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @PostMapping("/customer/")
    public ResponseEntity<Void> createAccountCustomer(@RequestBody UserRequestDto userRequestDto){
        userHandler.createAccountCustomer(userRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
