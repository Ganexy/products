package com.store.products.application.prices.handler;

import com.store.products.domain.prices.repository.PriceRepository;
import com.store.products.infrastructure.rest.prices.dto.PricesResponse;
import com.store.products.infrastructure.rest.prices.mapper.PricesMapper;
import org.springframework.http.ResponseEntity;


import java.time.LocalDateTime;
import java.util.Optional;

public class GetPricesHandler {

    private final PriceRepository priceRepository;
    private final PricesMapper mapper;

    public GetPricesHandler(PriceRepository priceRepository, PricesMapper mapper) {
        this.priceRepository = priceRepository;
        this.mapper = mapper;
    }

    public Optional<PricesResponse> handle(Long productId, String brandId, String applicationDate) {

        var dateTime = LocalDateTime.parse(applicationDate);

        return mapper.toResponse(priceRepository.findApplicablePrice(productId, brandId, dateTime));
    }

}
