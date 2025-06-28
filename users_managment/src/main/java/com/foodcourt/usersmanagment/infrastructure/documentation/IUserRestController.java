package com.foodcourt.usersmanagment.infrastructure.documentation;

import com.foodcourt.usersmanagment.application.dto.request.OwnerRequestDto;
import com.foodcourt.usersmanagment.application.dto.request.UserRequestDto;
import com.foodcourt.usersmanagment.application.dto.response.UserResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import static com.foodcourt.usersmanagment.infrastructure.documentation.ApiResponsesOKExamples.SAVE_USER_EXAMPLE;

public interface IUserRestController {

    @Operation(
            summary = "Create a new user",
            description = "Creates a new user with the provided information in the request body.",
            tags = {"User Management"}
    )

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Owner created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Owner already exists", content = @Content)

    })
    ResponseEntity<Void> saveUser(
            @RequestBody(description = "Request body containing the details of the owner to be created.",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserRequestDto.class),
                            examples = @ExampleObject(
                                    name = "OwnerRequestDtoExample",
                                    value = SAVE_USER_EXAMPLE)
                    )) UserRequestDto requestDto
    );

    @Operation(
            summary = "Get user by ID",
            description = "Retrieves the user information based on the provided user ID.",
            tags = {"User Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User found and returned successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content
            )
    })
    ResponseEntity<UserResponseDto> getUserById(
            @Parameter(description = "ID of the user to retrieve", required = true)
            @PathVariable Long id
    );

    @Operation(
            summary = "Verify user's role",
            description = "Checks if the user with the given ID has the specified role.",
            tags = {"User Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Role verification result returned successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(type = "boolean", example = "true")
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content
            )
    })
    ResponseEntity<Boolean> verifyUserRol(
            @Parameter(description = "ID of the user to verify", required = true)
            @PathVariable String dni,
            @Parameter(description = "Role to verify for the user", required = true)
            @PathVariable String rol
    );

    @Operation(
            summary = "Create a new employee",
            description = "Creates a new user with the provided information in the request body.",
            tags = {"User Management"}
    )

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Employee created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Employee already exists", content = @Content)

    })
    ResponseEntity<Void> createAccountEmployee(
            @RequestBody(description = "Request body containing the details of the employee to be created.",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserRequestDto.class),
                            examples = @ExampleObject(
                                    name = "Employee request dto example",
                                    value = SAVE_USER_EXAMPLE)
                    )) OwnerRequestDto requestDto
    );

    @Operation(
            summary = "Get user by Dni",
            description = "Retrieves the user information based on the provided user Dni.",
            tags = {"User Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User found and returned successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content
            )
    })
    ResponseEntity<UserResponseDto> getUserByDni(
            @Parameter(description = "Dni of the user to retrieve", required = true)
            @PathVariable String dni
    );

    @Operation(
            summary = "Create a new customer",
            description = "Creates a new user with the provided information in the request body.",
            tags = {"User Management"}
    )

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Customer created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Customer already exists", content = @Content)

    })
    ResponseEntity<Void> createAccountCustomer(
            @RequestBody(description = "Request body containing the details of the customer to be created.",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserRequestDto.class),
                            examples = @ExampleObject(
                                    name = "Customer request dto example",
                                    value = SAVE_USER_EXAMPLE)
                    )) UserRequestDto requestDto
    );
}
