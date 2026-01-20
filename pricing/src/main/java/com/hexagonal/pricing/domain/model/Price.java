package com.hexagonal.pricing.domain.model;

import java.time.LocalDateTime;

public record Price(
        Long brandId,
        Long productId,
        Long priceList,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Integer priority,
        Double price,
        String currency) {

}
