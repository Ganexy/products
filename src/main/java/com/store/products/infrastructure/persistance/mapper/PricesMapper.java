package com.store.products.infrastructure.persistance.mapper;

import com.store.products.domain.prices.entity.Price;
import com.store.products.infrastructure.persistance.dto.PriceDTO;
import org.springframework.stereotype.Component;

@Component
public class PricesMapper {


    public Price toDomain(PriceDTO response) {
        return new Price(
                response.getId(),
                response.getBrandId(),
                response.getStartDate(),
                response.getEndDate(),
                response.getPriceList(),
                response.getProductId(),
                response.getPriority(),
                response.getPrice(),
                response.getCurrency()
        );
    }
}
