package com.store.products.infrastructure.rest.prices.dto;

import java.time.LocalDateTime;

public class PricesRequest {
    private String productId;
    private String brandId;
    private LocalDateTime startDate;

    public PricesRequest(String productId, String brandId, LocalDateTime startDate) {
        this.productId = productId;
        this.brandId = brandId;
        this.startDate = startDate;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }
}

