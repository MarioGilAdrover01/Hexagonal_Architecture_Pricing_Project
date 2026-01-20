package com.hexagonal.pricing.application.ports.input;

import java.time.LocalDateTime;

import com.hexagonal.pricing.domain.model.Price;

public interface PriceServicePort {

    public Price getPriceByProductIdAndBrandIdAndDate(Long productId, Long brandId, LocalDateTime applicationDate);

}
