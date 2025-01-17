package com.store.products.infrastructure.rest.prices;

import com.store.products.application.prices.handler.GetPricesHandler;
import com.store.products.domain.prices.entity.Price;
import com.store.products.infrastructure.rest.dto.PriceResponse;
import com.store.products.infrastructure.rest.mapper.PricesRestMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductsPriceController {

    private final GetPricesHandler getPricesHandler;

    private final PricesRestMapper mapper;

    public ProductsPriceController(GetPricesHandler getPricesHandler, PricesRestMapper mapper) {
        this.getPricesHandler = getPricesHandler;
        this.mapper = mapper;
    }

    @Operation(summary = "Get the price of a product", description = "Get the price of a product in the desired date")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Get the price of a product in the desired date"),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    @GetMapping("/price")
    public ResponseEntity<PriceResponse> getPrice(
            @RequestParam String productId,
            @RequestParam String brandId,
            @RequestParam String applicationDate
    ) {
        Price price = getPricesHandler.handle(productId, brandId, applicationDate);

        return ResponseEntity.ok(this.mapper.fromDomain(price));
    }
}
