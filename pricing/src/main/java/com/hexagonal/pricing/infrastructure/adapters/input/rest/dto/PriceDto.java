package com.hexagonal.pricing.infrastructure.adapters.input.rest.dto;

import java.time.LocalDateTime;

import com.hexagonal.pricing.domain.model.Price;

public record PriceDto(
        Long brandId,
        Long productId,
        Long priceList,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Integer priority,
        Double price,
        String currency) {

    public static PriceDto fromDomain(Price price) {
        return new PriceDto(
                price.brandId(),
                price.productId(),
                price.priceList(),
                price.startDate(),
                price.endDate(),
                price.priority(),
                price.price(),
                price.currency());
    }
}
