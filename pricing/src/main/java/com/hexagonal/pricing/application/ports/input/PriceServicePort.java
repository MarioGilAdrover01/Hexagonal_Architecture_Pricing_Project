package com.hexagonal.pricing.application.ports.input;

import java.time.LocalDateTime;

import com.hexagonal.pricing.application.ports.input.dto.PriceDto;

public interface PriceServicePort {

    public PriceDto getPriceByProductIdAndBrandIdAndDate(Long productId, Long brandId, LocalDateTime applicationDate);

}
