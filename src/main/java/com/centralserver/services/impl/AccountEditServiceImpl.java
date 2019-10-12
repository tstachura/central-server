package com.centralserver.services.impl;

import com.centralserver.dto.user.UserEditDto;
import com.centralserver.exception.DatabaseErrorException;
import com.centralserver.exception.EntityNotInDatabaseException;
import com.centralserver.exception.EntityOptimisticLockException;
import com.centralserver.model.users.Address;
import com.centralserver.model.users.User;
import com.centralserver.model.users.UserRole;
import com.centralserver.model.users.Userdata;
import com.centralserver.repositories.UserRepository;
import com.centralserver.repositories.UserRoleRepository;
import com.centralserver.repositories.UserdataRepository;
import com.centralserver.services.AccountEditService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountEditServiceImpl implements AccountEditService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserdataRepository userdataRepository;

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('ACCOUNT_UPDATE_ADMIN')")
    public void updateAccountbyAdmin(UserEditDto userEditDto) throws EntityNotInDatabaseException, EntityOptimisticLockException, DatabaseErrorException {
        User user = userRepository.findById(userEditDto.getId()).orElseThrow((() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT)));
        if (userRepository.findByUsername(userEditDto.getUsername()).isPresent() && !user.getUsername().equals(userEditDto.getUsername())) {
            throw new DatabaseErrorException(DatabaseErrorException.USERNAME_TAKEN);
        }
        userRepository.detach(user);
        user.setUsername(userEditDto.getUsername());
        user.setAccountLocked(false);
        user.setCredentialsExpired(false);
        user.setVersion(userEditDto.getVersionUser());

        if (userEditDto.getRoles() != null) {
            List<UserRole> userRoles = Lists.newArrayList(userRoleRepository.findAll()).stream().filter(x -> userEditDto.getRoles().stream().anyMatch(name -> name.equals(x.getName())))
                    .collect(Collectors.toList());
            user.setUserRoles(userRoles);
        }
        Userdata userdata = userdataRepository.findById(user.getUserdata().getId()).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        userdataRepository.detach(userdata);
        if (userdataRepository.findByEmail(userEditDto.getEmail()).isPresent() && !userdata.getEmail().equals(userEditDto.getEmail())) {
            throw new DatabaseErrorException(DatabaseErrorException.EMAIL_TAKEN);
        }
        userdata.setEmail(userEditDto.getEmail());
        userdata.setSurname(userEditDto.getSurname());
        userdata.setPosition(userEditDto.getPosition());
        userdata.setName(userEditDto.getName());
        userdata.setWorkplace(userEditDto.getWorkplace());
        userdata.setDateOfJoin(Calendar.getInstance());
        Address address = new Address();
        address.setFlatNumber(userEditDto.getFlatNumber());
        address.setBuildingNumber(userEditDto.getHouseNumber());
        address.setStreet(userEditDto.getStreet());
        address.setCity(userEditDto.getCity());
        userdata.setAddress(address);
        userdata.setVersion(userEditDto.getVersionUserdata());

        try {
            userdataRepository.save(userdata);
            user.setUserdata(userdata);
            userRepository.save(user);
        } catch (ObjectOptimisticLockingFailureException e) {
            throw new EntityOptimisticLockException(EntityOptimisticLockException.OPTIMISTIC_LOCK);
        }
    }
}
