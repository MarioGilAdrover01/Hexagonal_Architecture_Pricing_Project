package com.hexagonal.pricing.domain.exception;

import java.time.LocalDateTime;

public class PriceNotFoundException extends RuntimeException {
    
    public PriceNotFoundException(Long productId, Long brandId, LocalDateTime applicationDate) {
        super(String.format("Applicable price not found for product ID " + productId 
                + ", brand ID " + brandId 
                + " and application date " + applicationDate.toString()));
    }
}
