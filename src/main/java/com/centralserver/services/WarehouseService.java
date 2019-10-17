package com.centralserver.services;

import com.centralserver.dto.WarehouseDto;
import com.centralserver.exception.DatabaseErrorException;
import com.centralserver.exception.EntityNotInDatabaseException;
import com.centralserver.exception.base.SystemBaseException;
import com.centralserver.model.products.Warehouse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface WarehouseService {

    @Transactional(readOnly = true, propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('WAREHOUSE_READ')")
    Warehouse getWarehouse(Long id) throws EntityNotInDatabaseException;

    @Transactional(readOnly = true, propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('WAREHOUSE_LIST_READ')")
    List<Warehouse> getAll();

    @Transactional
    @PreAuthorize("hasAuthority('WAREHOUSE_CREATE')")
    void createWarehouse(WarehouseDto warehouseDto) throws DatabaseErrorException;

    @Transactional
    @PreAuthorize("hasAuthority('WAREHOUSE_UPDATE')")
    void updateWarehouse(WarehouseDto warehouseDto) throws EntityNotInDatabaseException, DatabaseErrorException;

    @Transactional
    @PreAuthorize("hasAuthority('WAREHOUSE_DELETE')")
    void deleteWarehouseById(Long id) throws EntityNotInDatabaseException;
}
