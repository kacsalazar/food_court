package com.foodcourt.traceabilitymanagement.infrastructure.documentation;

import com.foodcourt.traceabilitymanagement.application.dto.response.EmployeeRankingResponse;
import com.foodcourt.traceabilitymanagement.application.dto.response.TraceabilityResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ITraceabilityRestController {

    @Operation(
            summary = "Get traceability of an order",
            description = "Retrieves the full traceability history of a specific order by its ID. Only accessible to users with the CUSTOMER role.",
            tags = {"Order Management", "Traceability"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Traceability data returned successfully",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = TraceabilityResponse.class))
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Order not found or has no traceability records",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden - User does not have CUSTOMER role or access to this order",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content
            )
    })
    ResponseEntity<List<TraceabilityResponse>> getTraceabilityByOrderId(
            @Parameter(
                    name = "orderId",
                    description = "ID of the order to retrieve traceability for",
                    required = true,
                    example = "1234",
                    schema = @Schema(type = "integer")
            )
            @PathVariable Long orderId
    );

    @Operation(
            summary = "Get order processing time",
            description = "Returns the total processing time of an order (from creation to delivery). Only accessible to users with the OWNER role.",
            tags = {"Order Management", "Traceability"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Order processing time returned successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(type = "string", example = "00:34:12")
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Order not found or no traceability data available",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden - User does not have OWNER role",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content
            )
    })
    ResponseEntity<String> getOrderProcessingTime(
            @Parameter(
                    name = "orderId",
                    description = "ID of the order to calculate processing time for",
                    required = true,
                    example = "1234",
                    schema = @Schema(type = "integer")
            )
            @PathVariable Long orderId
    );

    @Operation(
            summary = "Get employee ranking by restaurant",
            description = "Retrieves a list of employees ranked by order performance for a specific restaurant. Only accessible to users with the OWNER role.",
            tags = {"Order Management", "Employee Ranking"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Employee ranking retrieved successfully",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = EmployeeRankingResponse.class))
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Restaurant not found or no ranking data available",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden - User does not have OWNER role or does not own the restaurant",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content
            )
    })
    ResponseEntity<List<EmployeeRankingResponse>> getRankingForOrderByEmployeeId(
            @Parameter(
                    name = "restaurantId",
                    description = "ID of the restaurant to retrieve employee rankings for",
                    required = true,
                    example = "45",
                    schema = @Schema(type = "integer")
            )
            @PathVariable Long restaurantId
    );
}
