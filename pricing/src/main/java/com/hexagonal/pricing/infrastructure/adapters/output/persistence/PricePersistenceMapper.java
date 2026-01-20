package com.hexagonal.pricing.infrastructure.adapters.output.persistence;

import com.hexagonal.pricing.domain.model.Price;

public class PricePersistenceMapper {

    public static Price toDomain(PriceEntity entity) {
        if (entity == null)
            return null;

        return new Price(
                entity.getBrandId(),
                entity.getProductId(),
                entity.getPriceList(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getPriority(),
                entity.getPrice(),
                entity.getCurrency());
    }

    public static PriceEntity toEntity(Price domain) {
        if (domain == null)
            return null;

        PriceEntity entity = new PriceEntity();
        entity.setBrandId(domain.brandId());
        entity.setProductId(domain.productId());
        entity.setPriceList(domain.priceList());
        entity.setStartDate(domain.startDate());
        entity.setEndDate(domain.endDate());
        entity.setPriority(domain.priority());
        entity.setPrice(domain.price());
        entity.setCurrency(domain.currency());

        return entity;
    }

}
