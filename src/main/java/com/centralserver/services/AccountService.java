package com.centralserver.services;

import com.centralserver.dto.AccountDto;
import com.centralserver.dto.RegistrationDto;
import com.centralserver.exception.DatabaseErrorException;
import com.centralserver.exception.EntityNotInDatabaseException;
import com.centralserver.exception.EntityOptimisticLockException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;


public interface AccountService {

    @Transactional
    @PreAuthorize("hasAuthority('ACCOUNT_UPDATE_ADMIN')")
    void updateAccountByAdmin(AccountDto accountDto) throws EntityNotInDatabaseException, EntityOptimisticLockException, DatabaseErrorException;

    @Transactional
    @PreAuthorize("hasAuthority('USER_CREATE')")
    void registerNewUserAccount(RegistrationDto data, boolean verified) throws EntityNotInDatabaseException, DatabaseErrorException;
}
