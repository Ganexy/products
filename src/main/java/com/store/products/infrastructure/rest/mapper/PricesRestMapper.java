package com.store.products.infrastructure.rest.mapper;

import com.store.products.domain.prices.entity.Price;
import com.store.products.infrastructure.rest.dto.PriceResponse;
import org.springframework.stereotype.Component;

@Component
public class PricesRestMapper {
    public PriceResponse fromDomain(Price price) {
        var response = new PriceResponse();
        response.setProductId(price.productId());
        response.setBrandId(price.brandId());
        response.setPriceList(price.priceList());
        response.setStartDate(price.startDate());
        response.setEndDate(price.endDate());
        response.setPrice(price.price());

        return response;
    }
}
