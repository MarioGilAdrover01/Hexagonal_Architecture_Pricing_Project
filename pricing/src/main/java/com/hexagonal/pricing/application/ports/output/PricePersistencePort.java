package com.hexagonal.pricing.application.ports.output;

import java.time.LocalDateTime;
import java.util.List;

import com.hexagonal.pricing.domain.model.Price;

public interface PricePersistencePort {

    public List<Price> findByProductIdAndBrandIdAndDate(long productId, long brandId, LocalDateTime applicationDate);

}
