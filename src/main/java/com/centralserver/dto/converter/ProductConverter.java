package com.centralserver.dto.converter;

import com.centralserver.dto.ProductDto;
import com.centralserver.model.Warehouse;
import com.centralserver.model.enums.ProductStatus;
import com.centralserver.model.products.Product;
import com.centralserver.model.products.ProductType;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ProductConverter {

    public static ProductDto toProductDto(Product product) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return ProductDto.builder()
                .id(product.getId())
                .serialNumber(product.getSerialNumber())
                .productTypeId(product.getProductType().getId())
                .lastUpdate(formatter.format(product.getLastUpdate().getTime()))
                .status(product.getStatus())
                .warehouseId(product.getWarehouse().getId())
                .build();
    }


    public static Product toProduct(ProductDto productDto, Warehouse warehouse, ProductType productType) {
        Product product = new Product();
        product.setStatus(ProductStatus.available);
        product.setWarehouse(warehouse);
        product.setDeleted(false);
        product.setSerialNumber(productDto.getSerialNumber());
        product.setLastUpdate(Calendar.getInstance());
        product.setCreateDate(Calendar.getInstance());
        product.setProductType(productType);
        return product;
    }
}
