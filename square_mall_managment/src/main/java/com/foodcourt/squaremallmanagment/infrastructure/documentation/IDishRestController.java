package com.foodcourt.squaremallmanagment.infrastructure.documentation;

import com.foodcourt.squaremallmanagment.application.dto.request.DishCreateRequest;
import com.foodcourt.squaremallmanagment.application.dto.request.DishRequestUpdateDto;
import com.foodcourt.squaremallmanagment.application.dto.request.DishStatusRequest;
import com.foodcourt.squaremallmanagment.application.dto.response.DishResponse;
import com.foodcourt.squaremallmanagment.application.dto.response.DishRestaurantResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
                    schema = @Schema(implementation = DishCreateRequest.class),
                    examples = @ExampleObject(
                            name = "Restaurant Request Dto Example",
                            value = SAVE_DISH_EXAMPLE)
            )
    )
    @RequestBody DishCreateRequest dishCreateRequest);

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
                            schema = @Schema(implementation = DishResponse.class)
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
    ResponseEntity<DishResponse> updateDish(
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
                            schema = @Schema(implementation = DishResponse.class)
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
    ResponseEntity<DishResponse> disableDish(
            @Parameter(description = "ID of the dish to update", required = true)
            @PathVariable Long id,

            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dish data to change status",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DishStatusRequest.class),
                            examples = @ExampleObject(
                                    name = "Restaurant Request Dto Example",
                                    value = CHANGE_DISH_STATUS_EXAMPLE)
                    )
            )
            @RequestBody DishStatusRequest dishStatusRequest
    );

    @Operation(
            summary = "Get dishes by category and restaurant",
            description = "Returns a paginated list of dishes from a specific restaurant, optionally filtered by category.",
            tags = {"Dish Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "List of dishes returned successfully",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = DishRestaurantResponse.class))
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Restaurant or dishes not found",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content
            )
    })
    ResponseEntity<List<DishRestaurantResponse>> getDishesByCategory(
            @Parameter(
                    name = "idCategory",
                    description = "Optional category ID to filter dishes",
                    required = false,
                    example = "3",
                    schema = @Schema(type = "integer")
            )
            @RequestParam(required = false) Long idCategory,

            @Parameter(
                    name = "idRestaurant",
                    description = "ID of the restaurant",
                    required = true,
                    example = "1",
                    schema = @Schema(type = "integer")
            )
            @PathVariable Long idRestaurant,

            @Parameter(
                    name = "page",
                    description = "Page number (starts from 0)",
                    required = false,
                    example = "0",
                    schema = @Schema(type = "integer", defaultValue = "0")
            )
            @RequestParam(defaultValue = "0") Integer page,

            @Parameter(
                    name = "size",
                    description = "Number of records per page",
                    required = false,
                    example = "10",
                    schema = @Schema(type = "integer", defaultValue = "10")
            )
            @RequestParam(defaultValue = "10") Integer size
    );

}

