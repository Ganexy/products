package com.store.products.domain.prices.entity;

public record Price(
        Long id,
        String brandId,
        String startDate,
        String endDate,
        String priceList,
        String productId,
        Integer priority,
        String price,
        String currency
) {
}
