package com.store.products.application.prices.handler;

import com.store.products.domain.prices.entity.Price;
import com.store.products.domain.prices.repository.PriceRepository;
import com.store.products.infrastructure.rest.exception.PriceNotFoundException;
import com.store.products.infrastructure.rest.prices.dto.PriceResponse;
import com.store.products.infrastructure.rest.prices.mapper.PricesMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@DataJpaTest
@Sql(scripts = {"/schema.sql", "/data.sql"})
class GetPricesHandlerTest {

    @Mock
    private PriceRepository priceRepository;

    @Mock
    private PricesMapper pricesMapper;

    @InjectMocks
    private GetPricesHandler getPricesHandler;

    @Test
    void handle_should_return_correct_price() {
        String productId = "35455";
        String brandId = "1";
        String applicationDate = "2020-06-14 10:00:00";
        String expectedPrice = "25.45";

        var price = new Price();
        price.setProductId(productId);
        price.setBrandId(brandId);
        price.setPrice(expectedPrice);
        price.setStartDate("2020-06-14 00:00:00");
        price.setEndDate("2020-12-31 23:59:59");
        price.setCurrency("EUR");

        var response = new PriceResponse();
        response.setProductId(productId);
        response.setBrandId(brandId);
        response.setPrice(expectedPrice);
        response.setStartDate("2020-06-14 00:00:00");
        response.setEndDate("2020-12-31 23:59:59");

        when(priceRepository.findApplicablePrice(productId, brandId, applicationDate))
                .thenReturn(price);
        when(pricesMapper.toResponse(price)).thenReturn(response);

        var result = getPricesHandler.handle(productId, brandId, applicationDate);

        assertNotNull(result);
        assertEquals(productId, result.getProductId());
        assertEquals(expectedPrice, result.getPrice());
        assertEquals("2020-06-14 00:00:00", result.getStartDate());

        verify(priceRepository, times(1)).findApplicablePrice(productId, brandId, applicationDate);
        verify(pricesMapper, times(1)).toResponse(price);
    }

    @Test
    void handle_should_throw_exception_when_requirements_does_not_exist() {
        // Arrange
        String productId = "99999";
        String brandId = "1";
        String applicationDate = "2020-06-14 10:00:00";

        when(priceRepository.findApplicablePrice(productId, brandId, applicationDate))
                .thenThrow(new PriceNotFoundException("Price not found"));

        assertThrows(PriceNotFoundException.class, () ->
                getPricesHandler.handle(productId, brandId, applicationDate)
        );

        verify(priceRepository, times(1)).findApplicablePrice(productId, brandId, applicationDate);
        verify(pricesMapper, times(0)).toResponse(any());
    }

}