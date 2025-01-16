package com.store.products.domain.prices.repository;

import com.store.products.domain.prices.entity.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepository {

    Optional<Price> findApplicablePrice(Long productId, String brandId, LocalDateTime applicationDate);
}
