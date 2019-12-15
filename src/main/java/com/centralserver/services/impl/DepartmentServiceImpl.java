package com.centralserver.services.impl;

import com.centralserver.dto.DepartmentDto;
import com.centralserver.dto.converter.DepartmentConverter;
import com.centralserver.exception.DatabaseErrorException;
import com.centralserver.exception.EntityNotInDatabaseException;
import com.centralserver.kafka.producer.KafkaProducer;
import com.centralserver.model.products.Department;
import com.centralserver.repositories.DepartmentRepository;
import com.centralserver.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('WAREHOUSE_READ')")
    public Department getWarehouse(UUID id) throws EntityNotInDatabaseException {
        Department department = departmentRepository.findById(id).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        return department.isDeleted() ? null : department;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('WAREHOUSE_LIST_READ')")
    public List<Department> getAll() {
        return departmentRepository.findAll().stream().filter(warehouse -> !warehouse.isDeleted()).collect(Collectors.toList());
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('WAREHOUSE_CREATE')")
    public void createWarehouse(DepartmentDto warehouseDto) throws DatabaseErrorException {
        try {
            Department department = new Department();
            departmentRepository.saveAndFlush(DepartmentConverter.toWarehouse(warehouseDto, department));
        } catch (PersistenceException e) {
            throw new DatabaseErrorException(DatabaseErrorException.WAREHOUSE_NAME_TAKEN);
        }
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('WAREHOUSE_UPDATE')")
    public void updateWarehouse(DepartmentDto warehouseDto) throws EntityNotInDatabaseException, DatabaseErrorException {
        try {
            Department oldDepartment = departmentRepository.findById(warehouseDto.getId()).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
            departmentRepository.detach(oldDepartment);
            departmentRepository.saveAndFlush(DepartmentConverter.toWarehouse(warehouseDto, oldDepartment));
        } catch (PersistenceException e) {
            throw new DatabaseErrorException(DatabaseErrorException.WAREHOUSE_NAME_TAKEN);
        }
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('WAREHOUSE_DELETE')")
    public void deleteWarehouseById(UUID id) throws EntityNotInDatabaseException {
        departmentRepository.findById(id).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT)).setDeleted(true);
    }

}
