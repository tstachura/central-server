package com.centralserver.repositories.impl;

import com.centralserver.model.users.Userdata;
import com.centralserver.repositories.custom_interface.CustomUserdataRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


public class UserdataRepositoryImpl implements CustomUserdataRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void detach(Userdata entity) {
        entityManager.detach(entity);
    }
}
