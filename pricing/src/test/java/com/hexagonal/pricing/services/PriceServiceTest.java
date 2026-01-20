package com.hexagonal.pricing.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hexagonal.pricing.application.ports.output.PricePersistencePort;
import com.hexagonal.pricing.application.service.PriceService;
import com.hexagonal.pricing.domain.exception.PriceNotFoundException;
import com.hexagonal.pricing.domain.model.Price;

public class PriceServiceTest {

    @Mock
    private PricePersistencePort persisence;

    @InjectMocks
    private PriceService service;

    private static final Long BRAND_ID = 1L;
    private static final Long PRODUCT_ID = 35455L;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_service_returnsPriceFromRepository() {
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0);

        Price expectedPrice = new Price(
                BRAND_ID, PRODUCT_ID, 1L,
                LocalDateTime.of(2020, 6, 14, 0, 0),
                LocalDateTime.of(2020, 12, 31, 23, 59, 59),
                0,
                Double.parseDouble("35.50"),
                "EUR");

        when(persisence.findTopByProductIdAndBrandIdAndDate(BRAND_ID, PRODUCT_ID, date))
            .thenReturn(Optional.of(expectedPrice));

        Price result = service.getPriceByProductIdAndBrandIdAndDate(BRAND_ID, PRODUCT_ID, date);

        assertNotNull(result);
        assertEquals(BRAND_ID, result.brandId());
        assertEquals(PRODUCT_ID, result.productId());
        assertEquals(1L, result.priceList());
        assertEquals(35.50, result.price());
    }

    @Test
    void shouldThrowExceptionWhenNoPriceFound() {
        LocalDateTime date = LocalDateTime.of(2020, 1, 1, 10, 0);

        when(persisence.findTopByProductIdAndBrandIdAndDate(BRAND_ID, PRODUCT_ID, date))
            .thenReturn(Optional.empty());

        assertThrows(PriceNotFoundException.class, 
                () -> service.getPriceByProductIdAndBrandIdAndDate(BRAND_ID, PRODUCT_ID, date));
    }
}
