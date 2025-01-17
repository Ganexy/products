package com.store.products.domain.prices.repository;

import com.store.products.domain.prices.entity.Price;

import java.time.LocalDateTime;

public interface PriceRepository {

    Price findApplicablePrice(String productId, String brandId, LocalDateTime applicationDate);
}
