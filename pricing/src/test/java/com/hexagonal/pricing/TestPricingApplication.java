package com.hexagonal.pricing;

import org.springframework.boot.SpringApplication;

public class TestPricingApplication {

	public static void main(String[] args) {
		SpringApplication.from(PricingApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
