package com.store.products.domain.prices.entity;

import java.time.LocalDateTime;

public record Price(
        Long id,
        String brandId,
        LocalDateTime startDate,
        LocalDateTime endDate,
        String priceList,
        String productId,
        Integer priority,
        Double price,
        Currency currency
) {
}
