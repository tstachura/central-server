package com.centralserver.services;

import com.centralserver.dto.DepartmentDto;
import com.centralserver.exception.DatabaseErrorException;
import com.centralserver.exception.EntityNotInDatabaseException;
import com.centralserver.model.products.Department;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface DepartmentService {

    @Transactional(readOnly = true, propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('DEPARTMENT_READ')")
    Department getWarehouse(UUID id) throws EntityNotInDatabaseException;

    @Transactional(readOnly = true, propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('DEPARTMENT_LIST_READ')")
    List<Department> getAll();

    @Transactional
    @PreAuthorize("hasAuthority('DEPARTMENT_CREATE')")
    void createWarehouse(DepartmentDto warehouseDto) throws DatabaseErrorException;

    @Transactional
    @PreAuthorize("hasAuthority('DEPARTMENT_UPDATE')")
    void updateWarehouse(DepartmentDto warehouseDto) throws EntityNotInDatabaseException, DatabaseErrorException;

    @Transactional
    @PreAuthorize("hasAuthority('DEPARTMENT_DELETE')")
    void deleteWarehouseById(UUID id) throws EntityNotInDatabaseException;
}
