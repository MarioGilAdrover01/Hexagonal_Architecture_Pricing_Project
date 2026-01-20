package com.hexagonal.pricing.infrastructure.adapters.input.rest;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hexagonal.pricing.application.ports.input.PriceServicePort;
import com.hexagonal.pricing.application.ports.input.dto.PriceDto;

@RestController
@RequestMapping("/api/prices")
public class PriceController {

    @Autowired
    private PriceServicePort service;

    @GetMapping()
    public PriceDto getPrice(
            @RequestParam Long productId,
            @RequestParam Long brandId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate) {

        return service.getPriceByProductIdAndBrandIdAndDate(productId, brandId, applicationDate);
    }

}
