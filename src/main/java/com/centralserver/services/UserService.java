package com.centralserver.services;

import com.centralserver.dto.PasswordInfoDto;
import com.centralserver.exception.EntityNotInDatabaseException;
import com.centralserver.exception.EntityOptimisticLockException;
import com.centralserver.exception.base.SystemBaseException;
import com.centralserver.model.users.User;
import com.centralserver.model.users.UserRole;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUser(String id) throws SystemBaseException;

    List<UserRole> getUserRoles(String name) throws SystemBaseException;

    @Transactional
    @PreAuthorize("hasAuthority('USER_DELETE')")
    void deleteUser(String username) throws EntityNotInDatabaseException;

    @Transactional
    @PreAuthorize("hasAuthority('PASSWORD_ADMIN_READ')")
    PasswordInfoDto getPasswordForAdmin(String username) throws EntityNotInDatabaseException;

    @Transactional
    @PreAuthorize("hasAuthority('PASSWORD_ADMIN_UPDATE')")
    void updatePasswordForAdmin(PasswordInfoDto passwordInfoForAdmin) throws EntityNotInDatabaseException, EntityOptimisticLockException;

    PasswordInfoDto getPassword(String username) throws SystemBaseException;

    void updatePassword(PasswordInfoDto passwordInfoDto, String username) throws SystemBaseException;


}
