package com.centralserver.services;

import com.centralserver.exception.EntityNotInDatabaseException;
import com.centralserver.exception.base.SystemBaseException;
import com.centralserver.model.Warehouse;
import com.centralserver.model.users.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface WarehouseService {

    @Transactional(readOnly = true, propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('WAREHOUSE_LIST_READ')")
    List<Warehouse> getAll();

    void createWarehouse(Warehouse warehouseAddDto) throws SystemBaseException;

    void updateWarehouse(Warehouse warehouseEditDto) throws SystemBaseException;

    void deleteWarehouseById(Long id) throws SystemBaseException;

    Warehouse getWarehouse(Long id) throws SystemBaseException;

}
