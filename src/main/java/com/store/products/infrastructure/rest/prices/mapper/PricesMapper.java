package com.store.products.infrastructure.rest.prices.mapper;

import com.store.products.domain.prices.entity.Price;
import com.store.products.infrastructure.rest.prices.dto.PriceResponse;
import org.springframework.stereotype.Component;

@Component
public class PricesMapper {

    public PriceResponse toResponse(Price price) {
        var response = new PriceResponse();
        response.setProductId(price.getProductId());
        response.setBrandId(price.getBrandId());
        response.setPriceList(price.getPriceList());
        response.setStartDate(price.getStartDate());
        response.setEndDate(price.getEndDate());
        response.setPrice(price.getPrice());
        return response;
    }
}
