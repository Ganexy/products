package com.store.products.application.prices.config;

import com.store.products.application.prices.handler.GetPricesHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        GetPricesHandler.class,
})
public class PricesHandlerConfiguration {
}
