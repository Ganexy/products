package com.store.products.infrastructure.config;

import com.store.products.application.prices.handler.GetPricesHandler;
import com.store.products.infrastructure.persistance.repository.JpaPriceRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        GetPricesHandler.class,
        JpaPriceRepository.class
})
public class PricesHandlerConfiguration {
}
