package com.store.products.domain.prices.repository;

import com.store.products.domain.prices.entity.Price;

import java.util.Optional;

public interface PriceRepository {

    Price findApplicablePrice(Long productId, String brandId, String applicationDate);
}
