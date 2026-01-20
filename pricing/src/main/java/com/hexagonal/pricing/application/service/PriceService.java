package com.hexagonal.pricing.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexagonal.pricing.application.ports.input.PriceServicePort;
import com.hexagonal.pricing.application.ports.output.PricePersistencePort;

@Service
public class PriceService implements PriceServicePort {

    @Autowired
    private PricePersistencePort persistence;

}
