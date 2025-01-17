package com.store.products.infrastructure.persistance.prices.repository;

import com.store.products.domain.prices.entity.Currency;
import com.store.products.domain.prices.entity.Price;
import com.store.products.domain.prices.exception.PriceNotFoundException;
import com.store.products.infrastructure.persistance.dto.PriceDTO;
import com.store.products.infrastructure.persistance.mapper.PricesMapper;
import com.store.products.infrastructure.persistance.repository.JpaPriceRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
class JpaPriceRepositoryIntegrationTest {

    @Autowired
    private JpaPriceRepository jpaPriceRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private PricesMapper pricesMapper;

    @Test
    @DisplayName("Debería retornar el precio aplicable si existe en la BD")
    void shouldReturnApplicablePriceIfExists() {
        PriceDTO priceDTO = new PriceDTO();
        priceDTO.setProductId("1");
        priceDTO.setBrandId("1");
        priceDTO.setStartDate(LocalDateTime.parse("2020-06-14T00:00:00"));
        priceDTO.setEndDate(LocalDateTime.parse("2020-06-14T23:59:59"));
        priceDTO.setPriority(1);
        priceDTO.setPriceList("1");
        priceDTO.setPrice(35.50);
        priceDTO.setCurrency(Currency.EUR);

        entityManager.persist(priceDTO);
        entityManager.flush();

        String productId = "1";
        String brandId = "1";
        LocalDateTime applicationDate = LocalDateTime.parse("2020-06-14T10:00:00");

        Price price = jpaPriceRepository.findApplicablePrice(productId, brandId, applicationDate);

        assertNotNull(price);
        assertEquals("1", price.productId());
        assertEquals("1", price.brandId());
        assertEquals(35.50, price.price());
    }

    @Test
    @DisplayName("Debería lanzar PriceNotFoundException si no existe precio aplicable")
    void shouldThrowExceptionIfNoPriceFound() {
       assertThrows(PriceNotFoundException.class, () -> {
            jpaPriceRepository.findApplicablePrice("999", "999", LocalDateTime.parse("2030-01-01T00:00:00"));
        });
    }
}
