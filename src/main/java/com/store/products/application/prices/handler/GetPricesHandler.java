package com.store.products.application.prices.handler;

import com.store.products.domain.prices.entity.Price;
import com.store.products.domain.prices.repository.PriceRepository;

public class GetPricesHandler {

    private final PriceRepository priceRepository;

    public GetPricesHandler(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public Price handle(String productId, String brandId, String applicationDate) {
        return priceRepository.findApplicablePrice(productId, brandId, applicationDate);
    }

}
