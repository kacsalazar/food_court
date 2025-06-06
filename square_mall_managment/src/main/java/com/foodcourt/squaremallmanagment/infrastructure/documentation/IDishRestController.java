package com.foodcourt.squaremallmanagment.infrastructure.documentation;

import com.foodcourt.squaremallmanagment.application.dto.request.DishRequestDto;
import com.foodcourt.squaremallmanagment.application.dto.request.DishRequestUpdateDto;
import com.foodcourt.squaremallmanagment.application.dto.response.DishResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import static com.foodcourt.squaremallmanagment.infrastructure.documentation.ApiResponsesOKExample.*;

public interface IDishRestController {

    @Operation(
            summary = "Create a new dish",
            description = "Saves a new dish based on the provided information.",
            tags = {"Dish Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Dish created successfully",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request data",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content
            )
    })
    ResponseEntity<Void> saveDish(
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Dish information to be created",
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = DishRequestDto.class),
                    examples = @ExampleObject(
                            name = "Restaurant Request Dto Example",
                            value = SAVE_DISH_EXAMPLE)
            )
    )
    @RequestBody DishRequestDto dishRequestDto );

    @Operation(
            summary = "Update a dish by its ID",
            description = "Updates an existing dish using the provided ID and updated dish data.",
            tags = {"Dish Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Dish updated successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DishResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Dish not found",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content
            )
    })
    ResponseEntity<DishResponseDto> updateDish(
            @Parameter(description = "ID of the dish to update", required = true)
            @PathVariable Long id,

            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dish data to update",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DishRequestUpdateDto.class),
                            examples = @ExampleObject(
                                    name = "Restaurant Request Dto Example",
                                    value = UPDATE_DISH_EXAMPLE)
                    )
            )
            @RequestBody DishRequestUpdateDto dishRequestUpdateDto
    );

}

