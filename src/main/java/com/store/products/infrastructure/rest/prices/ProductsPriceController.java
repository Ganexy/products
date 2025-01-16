package com.store.products.infrastructure.rest.prices;

import com.store.products.application.prices.handler.GetPricesHandler;
import com.store.products.infrastructure.rest.prices.dto.PricesResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductsPriceController {

    private final GetPricesHandler getPricesHandler;

    public ProductsPriceController(GetPricesHandler getPricesHandler) {
        this.getPricesHandler = getPricesHandler;
    }

    @Operation(summary = "Get the price of a product", description = "Get the price of a product in the desired date")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Get the price of a product in the desired date"),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    @GetMapping("/prices")
    public ResponseEntity<Optional<PricesResponse>> getPrice(
            @RequestParam Long productId,
            @RequestParam String brandId,
            @RequestParam String applicationDate
    ){
        return ResponseEntity.ok(getPricesHandler.handle(productId, brandId, applicationDate));
    }
}
