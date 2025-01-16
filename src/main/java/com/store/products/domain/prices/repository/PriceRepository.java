package com.store.products.domain.prices.repository;

import com.store.products.domain.prices.entity.Price;

public interface PriceRepository {

    Price findApplicablePrice(String productId, String brandId, String applicationDate);
}
