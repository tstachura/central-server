package com.centralserver.services.impl;

import com.centralserver.exception.DatabaseErrorException;
import com.centralserver.exception.EntityNotInDatabaseException;
import com.centralserver.model.Warehouse;
import com.centralserver.repositories.WarehouseRepository;
import com.centralserver.services.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('WAREHOUSE_READ')")
    public Warehouse getWarehouse(Long id) throws EntityNotInDatabaseException {
        Warehouse warehouse = warehouseRepository.findById(id).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        return warehouse.isDeleted() ? null : warehouse;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('WAREHOUSE_LIST_READ')")
    public List<Warehouse> getAll() {
        return warehouseRepository.findAll().stream().filter(warehouse -> !warehouse.isDeleted()).collect(Collectors.toList());
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('WAREHOUSE_CREATE')")
    public void createWarehouse(Warehouse warehouse) throws DatabaseErrorException {
        try {
            warehouseRepository.saveAndFlush(warehouse);
        } catch (PersistenceException e) {
            throw new DatabaseErrorException(DatabaseErrorException.WAREHOUSE_NAME_TAKEN);
        }

    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('WAREHOUSE_UPDATE')")
    public void updateWarehouse(Warehouse warehouse) throws EntityNotInDatabaseException, DatabaseErrorException {
        try {
            Warehouse beforeWarehouse = warehouseRepository.findById(warehouse.getId()).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
            warehouseRepository.detach(beforeWarehouse);
            warehouseRepository.saveAndFlush(warehouse);
        } catch (PersistenceException e) {
            throw new DatabaseErrorException(DatabaseErrorException.WAREHOUSE_NAME_TAKEN);
        }
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('WAREHOUSE_DELETE')")
    public void deleteWarehouseById(Long id) throws EntityNotInDatabaseException {
        warehouseRepository.findById(id).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT)).setDeleted(true);
    }

}
