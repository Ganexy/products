package com.store.products.domain.prices.repository;

import com.store.products.domain.prices.entity.Price;
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
    public Optional<Price> findApplicablePrice(Long productId, String brandId, LocalDateTime applicationDate) {

        var query = "SELECT p FROM Price p WHERE p.productId = :productId AND p.brandId = :brandId " +
                "AND :applicationDate BETWEEN p.startDate AND p.endDate " +
                "ORDER BY p.priority DESC";

        var debug = entityManager.createQuery(query, Price.class)
                .setParameter("productId", productId)
                .setParameter("brandId", brandId)
                .setParameter("applicationDate", applicationDate)
                .setMaxResults(1)
                .getResultStream()
                .findFirst();

        return debug;
    }
}
