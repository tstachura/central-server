package com.centralserver.services;

import com.centralserver.dto.user.RegistrationDto;
import com.centralserver.exception.DatabaseErrorException;
import com.centralserver.exception.EntityNotInDatabaseException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

public interface RegistrationService {

    @Transactional
    @PreAuthorize("hasAuthority('USER_CREATE')")
    void registerNewUserAccount(RegistrationDto data, boolean verified) throws EntityNotInDatabaseException, DatabaseErrorException;
}
