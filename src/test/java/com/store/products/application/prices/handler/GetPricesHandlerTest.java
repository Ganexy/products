package com.store.products.application.prices.handler;

import com.store.products.domain.prices.entity.Price;
import com.store.products.domain.prices.exception.PriceNotFoundException;
import com.store.products.domain.prices.repository.PriceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class GetPricesHandlerTest {

    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private GetPricesHandler getPricesHandler;

    @Test
    void handle_should_return_correct_price() {
        String productId = "35455";
        String brandId = "1";
        String applicationDate = "2020-06-14 10:00:00";
        String expectedPrice = "25.45";

        var price = new Price(
                1L,
                brandId,
                "2020-06-14 00:00:00",
                "2020-06-14 00:00:00",
                "1",
                "35455",
                0,
                expectedPrice,
                "EUR"
        );

        when(priceRepository.findApplicablePrice(productId, brandId, applicationDate))
                .thenReturn(price);

        var result = getPricesHandler.handle(productId, brandId, applicationDate);

        assertNotNull(result);
        assertEquals(productId, result.productId());
        assertEquals(expectedPrice, result.price());
        assertEquals("2020-06-14 00:00:00", result.startDate());

        verify(priceRepository, times(1)).findApplicablePrice(productId, brandId, applicationDate);
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
    }

}