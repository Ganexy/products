package com.store.products.infrastructure.persistance.repository;

import com.store.products.domain.prices.entity.Price;
import com.store.products.domain.prices.exception.PriceNotFoundException;
import com.store.products.domain.prices.repository.PriceRepository;
import com.store.products.infrastructure.persistance.dto.PriceDTO;
import com.store.products.infrastructure.persistance.mapper.PricesMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class JpaPriceRepository implements PriceRepository {

    private final PricesMapper mapper;

    @PersistenceContext
    private EntityManager entityManager;

    public JpaPriceRepository(PricesMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Price findApplicablePrice(String productId, String brandId, String applicationDate) {

        var query = "SELECT p FROM PriceDTO p WHERE p.productId = :productId AND p.brandId = :brandId " +
                "AND :applicationDate BETWEEN p.startDate AND p.endDate " +
                "ORDER BY p.priority DESC";

        var response = entityManager.createQuery(query, PriceDTO.class)
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
        return this.mapper.toDomain(response);
    }
}
