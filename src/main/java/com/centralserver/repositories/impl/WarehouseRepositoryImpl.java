package com.centralserver.repositories.impl;

import com.centralserver.model.products.Warehouse;
import com.centralserver.repositories.custom_interface.CustomWarehouseRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


public class WarehouseRepositoryImpl implements CustomWarehouseRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void detach(Warehouse entity) {
        entityManager.detach(entity);
    }
}
