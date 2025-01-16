package com.store.products.application.prices.handler;

import com.store.products.domain.prices.repository.PriceRepository;
import com.store.products.infrastructure.rest.prices.dto.PriceResponse;
import com.store.products.infrastructure.rest.prices.mapper.PricesMapper;

import java.util.Optional;

public class GetPricesHandler {

    private final PriceRepository priceRepository;
    private final PricesMapper mapper;

    public GetPricesHandler(PriceRepository priceRepository, PricesMapper mapper) {
        this.priceRepository = priceRepository;
        this.mapper = mapper;
    }

    public PriceResponse handle(Long productId, String brandId, String applicationDate) {

        return mapper.toResponse(priceRepository.findApplicablePrice(productId, brandId, applicationDate));
    }

}
