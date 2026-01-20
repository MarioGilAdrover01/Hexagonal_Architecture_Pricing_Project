package com.hexagonal.pricing.infrastructure.adapters.output.persistence;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hexagonal.pricing.application.ports.output.PricePersistencePort;
import com.hexagonal.pricing.domain.model.Price;

@Component
public class PricePersistenceAdapter implements PricePersistencePort {

    @Autowired
    private PriceRepository repository;

    @Override
    public Optional<Price> findTopByProductIdAndBrandIdAndDate(long productId, long brandId, LocalDateTime applicationDate) {
        Optional<PriceEntity> price = repository
                .findTopByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                    brandId,
                    productId,
                    applicationDate,
                    applicationDate);

        return Optional.of(
            price.isPresent() ? PricePersistenceMapper.toDomain(price.get()) : null
        );
    }

}
