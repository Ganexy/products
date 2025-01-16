package com.store.products.infrastructure.rest.prices.mapper;

import com.store.products.domain.prices.entity.Price;
import com.store.products.infrastructure.rest.prices.dto.PricesResponse;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PricesMapper {

    public static Optional<PricesResponse> toResponse(Optional<Price> optionalPrice) {
        return optionalPrice.map(price -> {
            PricesResponse response = new PricesResponse();
            response.setProductId(price.getProductId()); // Convertir String a Long si es necesario
            response.setBrandId(price.getBrandId());
            response.setPriceList(price.getPriceList());
            response.setStartDate(price.getStartDate());
            response.setEndDate(price.getEndDate());
            response.setPrice(price.getPrice());
            response.setCurrency(price.getCurrency());
            return response;
        });
    }
}
