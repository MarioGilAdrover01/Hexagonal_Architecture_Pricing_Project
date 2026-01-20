package com.hexagonal.pricing.controllers;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.MissingServletRequestParameterException;

import com.hexagonal.pricing.domain.exception.PriceNotFoundException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static final String CONTROLLER_URL = "/api/prices";

    @Test
    void test1_requestAt10OnDay14() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(CONTROLLER_URL)
                .param("brandId", "1")
                .param("productId", "35455")
                .param("applicationDate", "2020-06-14T10:00:00"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.priceList").value(1))
            .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(35.50));
    }

    @Test
    void test2_requestAt16OnDay14() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(CONTROLLER_URL)
                .param("brandId", "1")
                .param("productId", "35455")
                .param("applicationDate", "2020-06-14T16:00:00"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.priceList").value(2))
            .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(25.45));
    }

    @Test
    void test3_requestAt21OnDay14() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(CONTROLLER_URL)
                .param("brandId", "1")
                .param("productId", "35455")
                .param("applicationDate", "2020-06-14T21:00:00"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.priceList").value(1))
            .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(35.50));
    }

    @Test
    void test4_requestAt10OnDay15() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(CONTROLLER_URL)
                .param("brandId", "1")
                .param("productId", "35455")
                .param("applicationDate", "2020-06-15T10:00:00"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.priceList").value(3))
            .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(30.50));
    }

    @Test
    void test5_requestAt21OnDay16() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(CONTROLLER_URL)
                .param("brandId", "1")
                .param("productId", "35455")
                .param("applicationDate", "2020-06-16T21:00:00"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.priceList").value(4))
            .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(38.95));
    }

    @Test
    void test_missingParameter_returns400() throws Exception {
        // Missing brandId
        mockMvc.perform(MockMvcRequestBuilders.get(CONTROLLER_URL)
                .param("productId", "35455")
                .param("applicationDate", "2020-06-14T10:00:00"))
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andExpect(result -> assertTrue(result.getResolvedException() 
                instanceof MissingServletRequestParameterException));

        // Missing productId
        mockMvc.perform(MockMvcRequestBuilders.get(CONTROLLER_URL)
                .param("brandId", "1")
                .param("applicationDate", "2020-06-14T10:00:00"))
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andExpect(result -> assertTrue(result.getResolvedException() 
                instanceof MissingServletRequestParameterException));

        // Missing applicationDate
        mockMvc.perform(MockMvcRequestBuilders.get(CONTROLLER_URL)
                .param("brandId", "1")
                .param("productId", "35455"))
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andExpect(result -> assertTrue(result.getResolvedException() 
                instanceof MissingServletRequestParameterException));
    }

    @Test
    void test_priceNotFound_returns404() throws Exception {
        // Date is not found in BBDD
        mockMvc.perform(MockMvcRequestBuilders.get(CONTROLLER_URL)
                .param("brandId", "1")
                .param("productId", "35455")
                .param("applicationDate", "2025-01-01T00:00:00"))
            .andExpect(MockMvcResultMatchers.status().isNotFound())
            .andExpect(result -> assertTrue(result.getResolvedException() 
                instanceof PriceNotFoundException));
    }
}
