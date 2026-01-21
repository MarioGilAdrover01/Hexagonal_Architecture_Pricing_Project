package com.hexagonal.pricing.application.service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexagonal.pricing.application.ports.input.PriceServicePort;
import com.hexagonal.pricing.application.ports.output.PricePersistencePort;
import com.hexagonal.pricing.domain.exception.PriceNotFoundException;
import com.hexagonal.pricing.domain.model.Price;

@Service
public class PriceService implements PriceServicePort {

    @Autowired
    private PricePersistencePort persistence;

    @Override
    public Price getPriceByProductIdAndBrandIdAndDate(
            Long productId, Long brandId, LocalDateTime applicationDate) {

        List<Price> applicablePrices = persistence.findByProductIdAndBrandIdAndDate(productId, brandId, applicationDate);

        Optional<Price> highestPriorityPrice = applicablePrices.stream()
            .sorted(Comparator.comparingInt(Price::priority).reversed())
            .findFirst();

        return highestPriorityPrice.orElseThrow(() -> new PriceNotFoundException(productId, brandId, applicationDate));
    }

}
