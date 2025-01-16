CREATE TABLE IF NOT EXISTS PRICES (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    BRAND_ID VARCHAR(50) NOT NULL,
    START_DATE TIMESTAMP NOT NULL,
    END_DATE TIMESTAMP NOT NULL,
    PRICE_LIST VARCHAR(50) NOT NULL,
    PRODUCT_ID VARCHAR(50) NOT NULL,
    PRIORITY VARCHAR(10) NOT NULL,
    PRICE VARCHAR(20) NOT NULL,
    CURRENCY VARCHAR(3) NOT NULL
);
