package com.hexagonal.pricing.infrastructure.adapters.output.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hexagonal.pricing.application.ports.output.PricePersistencePort;

@Component
public class PricePersistenceAdapter implements PricePersistencePort {

    @Autowired
    private PriceRepository repository;

}
