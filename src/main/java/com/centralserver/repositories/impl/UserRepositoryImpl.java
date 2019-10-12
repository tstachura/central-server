package com.centralserver.repositories.impl;

import com.centralserver.model.users.User;
import com.centralserver.repositories.custom_interface.CustomUserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


public class UserRepositoryImpl implements CustomUserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void detach(User user) {
        entityManager.detach(user);
    }
}
