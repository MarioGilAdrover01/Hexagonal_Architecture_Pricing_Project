package com.hexagonal.pricing.infrastructure.adapters.input.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexagonal.pricing.application.ports.input.PriceServicePort;

@RestController
@RequestMapping("/api/prices")
public class PriceController {

    @Autowired
    private PriceServicePort service;

}
