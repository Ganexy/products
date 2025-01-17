package com.store.products.infrastructure.rest.prices;

import com.store.products.application.prices.handler.GetPricesHandler;
import com.store.products.domain.prices.entity.Price;
import com.store.products.infrastructure.rest.dto.PriceResponse;
import com.store.products.infrastructure.rest.mapper.PricesRestMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Tag(name = "Price Management", description = "Endpoints related to product pricing")
@RestController
@RequestMapping("/products")
public class ProductsPriceController {

    private final GetPricesHandler getPricesHandler;

    private final PricesRestMapper mapper;

    public ProductsPriceController(GetPricesHandler getPricesHandler, PricesRestMapper mapper) {
        this.getPricesHandler = getPricesHandler;
        this.mapper = mapper;
    }

    @Operation(summary = "Get the price of a product", description = "Retrieve the applicable price of a product for a specific brand, product and application date.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Price retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PriceResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input parameters",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Price not found for the given inputs",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(example = """
                                    {
                                        "error": "Price not found",
                                        "message": "No price found for productId: 35455, brandId: 1, applicationDate: 2025-01-17T12:00"
                                    }
                                    """)
                    )),
            @ApiResponse(responseCode = "500", description = "Internal server error.",
                    content = @Content)
    })
    @GetMapping("/price")
    public ResponseEntity<PriceResponse> getPrice(
            @Parameter(description = "The unique identifier of the product", example = "35455", required = true)
            @RequestParam String productId,
            @Parameter(description = "The unique identifier of the brand", example = "1", required = true)
            @RequestParam String brandId,
            @Parameter(description = "The date and time for which the price is requested",
                    example = "2020-06-14T17:00:00", required = true)
            @RequestParam LocalDateTime applicationDate
    ) {
        Price price = getPricesHandler.handle(productId, brandId, applicationDate);

        return ResponseEntity.ok(this.mapper.fromDomain(price));
    }
}
