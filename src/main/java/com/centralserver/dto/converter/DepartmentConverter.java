package com.centralserver.dto.converter;

import com.centralserver.dto.DepartmentDto;
import com.centralserver.model.products.Department;

public class DepartmentConverter {

    public static DepartmentDto toWarehouseViewDto(Department department) {
        return DepartmentDto.builder()
                .id(department.getId())
                .name(department.getName())
                .version(department.getVersion())
                .build();
    }

    public static Department toWarehouse(DepartmentDto warehouseDto, Department oldDepartment) {
        oldDepartment.setName(warehouseDto.getName());
        oldDepartment.setVersion(warehouseDto.getVersion());
        return oldDepartment;
    }

}

