package com.centralserver.services;

import com.centralserver.dto.user.UserEditDto;
import com.centralserver.exception.DatabaseErrorException;
import com.centralserver.exception.EntityNotInDatabaseException;
import com.centralserver.exception.EntityOptimisticLockException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;


public interface AccountEditService {

    @Transactional
    @PreAuthorize("hasAuthority('ACCOUNT_UPDATE_ADMIN')")
    void updateAccountbyAdmin(UserEditDto userEditDto) throws EntityNotInDatabaseException, EntityOptimisticLockException, DatabaseErrorException;
}
