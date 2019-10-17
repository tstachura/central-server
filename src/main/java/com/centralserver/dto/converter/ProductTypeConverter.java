package com.centralserver.dto.converter;

import com.centralserver.dto.ProductTypeDto;
import com.centralserver.model.products.ProductType;

public class ProductTypeConverter {
    
    public static ProductTypeDto toProductTypeDto(ProductType productType) {
        return ProductTypeDto.builder()
                .id(productType.getId())
                .manufacture(productType.getManufacture())
                .name(productType.getName())
                .cost(productType.getCost())
                .version(productType.getVersion())
                .build();
    }

    public static ProductType toProductType(ProductTypeDto productTypeDto, ProductType oldProductType) {
        oldProductType.setCost(oldProductType.getCost());
        oldProductType.setManufacture(oldProductType.getManufacture());
        oldProductType.setName(oldProductType.getName());
        oldProductType.setVersion(oldProductType.getVersion());
        return oldProductType;
    }

}
