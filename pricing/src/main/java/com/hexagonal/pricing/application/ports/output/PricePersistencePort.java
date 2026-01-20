package com.hexagonal.pricing.application.ports.output;

import java.time.LocalDateTime;
import java.util.Optional;

import com.hexagonal.pricing.domain.model.Price;

public interface PricePersistencePort {

    public Optional<Price> findTopByProductIdAndBrandIdAndDate(long productId, long brandId, LocalDateTime applicationDate);

}
