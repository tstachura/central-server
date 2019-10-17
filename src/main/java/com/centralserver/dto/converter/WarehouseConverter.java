package com.centralserver.dto.converter;

import com.centralserver.dto.WarehouseDto;
import com.centralserver.model.products.Warehouse;

public class WarehouseConverter {

    public static WarehouseDto toWarehouseViewDto(Warehouse warehouse) {
        return WarehouseDto.builder()
                .id(warehouse.getId())
                .name(warehouse.getName())
                .version(warehouse.getVersion())
                .build();
    }

    public static Warehouse toWarehouse(WarehouseDto warehouseDto, Warehouse oldWarehouse) {
        oldWarehouse.setName(warehouseDto.getName());
        oldWarehouse.setVersion(warehouseDto.getVersion());
        return oldWarehouse;
    }

}

