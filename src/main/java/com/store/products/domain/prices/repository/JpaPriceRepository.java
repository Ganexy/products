package com.store.products.domain.prices.repository;

import com.store.products.domain.prices.entity.Price;
import com.store.products.infrastructure.rest.exception.PriceNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public class JpaPriceRepository implements PriceRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Price findApplicablePrice(Long productId, String brandId, String applicationDate) {

        var query = "SELECT p FROM Price p WHERE p.productId = :productId AND p.brandId = :brandId " +
                "AND :applicationDate BETWEEN p.startDate AND p.endDate " +
                "ORDER BY p.priority DESC";

        return entityManager.createQuery(query, Price.class)
                .setParameter("productId", productId)
                .setParameter("brandId", brandId)
                .setParameter("applicationDate", applicationDate)
                .setMaxResults(1)
                .getResultStream()
                .findFirst()
                .orElseThrow(() -> new PriceNotFoundException(
                        "No price found for productId: " + productId +
                        ", brandId: " + brandId +
                        ", applicationDate: " + applicationDate
                ));
    }
}
