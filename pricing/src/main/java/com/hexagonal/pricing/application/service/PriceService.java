package com.hexagonal.pricing.application.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexagonal.pricing.application.ports.input.PriceServicePort;
import com.hexagonal.pricing.application.ports.input.dto.PriceDto;
import com.hexagonal.pricing.application.ports.output.PricePersistencePort;
import com.hexagonal.pricing.domain.exception.PriceNotFoundException;
import com.hexagonal.pricing.domain.model.Price;

@Service
public class PriceService implements PriceServicePort {

    @Autowired
    private PricePersistencePort persistence;

    @Override
    public PriceDto getPriceByProductIdAndBrandIdAndDate(Long productId, Long brandId, LocalDateTime applicationDate) {
        Price price = persistence.findTopByProductIdAndBrandIdAndDate(
                productId, 
                brandId, 
                applicationDate)
            .orElseThrow(() -> new PriceNotFoundException(productId, brandId, applicationDate));

        return PriceDto.fromDomain(price);
    }

}
