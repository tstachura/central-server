package com.centralserver.services.impl;

import com.centralserver.dto.AccountDto;
import com.centralserver.dto.RegistrationDto;
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
import com.centralserver.services.AccountService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    @Qualifier("userPasswordEncoder")
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserdataRepository userdataRepository;

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('ACCOUNT_UPDATE_ADMIN')")
    public void updateAccountByAdmin(AccountDto accountDto) throws EntityNotInDatabaseException, EntityOptimisticLockException, DatabaseErrorException {
        User user = userRepository.findById(accountDto.getId()).orElseThrow((() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT)));
        if (userRepository.findByUsername(accountDto.getUsername()).isPresent() && !user.getUsername().equals(accountDto.getUsername())) {
            throw new DatabaseErrorException(DatabaseErrorException.USERNAME_TAKEN);
        }
        userRepository.detach(user);
        user.setUsername(accountDto.getUsername());
        user.setAccountLocked(false);
        user.setCredentialsExpired(false);
        user.setVersion(accountDto.getVersionUser());

        if (accountDto.getRoles() != null) {
            List<UserRole> userRoles = Lists.newArrayList(userRoleRepository.findAll()).stream().filter(x -> accountDto.getRoles().stream().anyMatch(name -> name.equals(x.getName())))
                    .collect(Collectors.toList());
            user.setUserRoles(userRoles);
        }
        Userdata userdata = userdataRepository.findById(user.getUserdata().getId()).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        userdataRepository.detach(userdata);
        if (userdataRepository.findByEmail(accountDto.getEmail()).isPresent() && !userdata.getEmail().equals(accountDto.getEmail())) {
            throw new DatabaseErrorException(DatabaseErrorException.EMAIL_TAKEN);
        }
        userdata.setEmail(accountDto.getEmail());
        userdata.setSurname(accountDto.getSurname());
        userdata.setPosition(accountDto.getPosition());
        userdata.setName(accountDto.getName());
        userdata.setWorkplace(accountDto.getWorkplace());
        userdata.setDateOfJoin(Calendar.getInstance());
        Address address = new Address();
        address.setFlatNumber(accountDto.getFlatNumber());
        address.setBuildingNumber(accountDto.getHouseNumber());
        address.setStreet(accountDto.getStreet());
        address.setCity(accountDto.getCity());
        userdata.setAddress(address);
        userdata.setVersion(accountDto.getVersionUserdata());

        try {
            userdataRepository.save(userdata);
            user.setUserdata(userdata);
            userRepository.save(user);
        } catch (ObjectOptimisticLockingFailureException e) {
            throw new EntityOptimisticLockException(EntityOptimisticLockException.OPTIMISTIC_LOCK);
        }
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('USER_CREATE')")
    public void registerNewUserAccount(final RegistrationDto data, boolean verified) throws EntityNotInDatabaseException, DatabaseErrorException {

        if (userdataRepository.findByEmail(data.getEmail()).isPresent()) {
            throw new DatabaseErrorException(DatabaseErrorException.EMAIL_TAKEN);
        }
        if (userRepository.findByUsername(data.getUsername()).isPresent()) {
            throw new DatabaseErrorException(DatabaseErrorException.USERNAME_TAKEN);
        }

        User user = new User();
        user.setUsername(data.getUsername());
        user.setPassword(passwordEncoder.encode(data.getPassword()));
        user.setEnabled(true);
        user.setAccountExpired(false);
        user.setAccountLocked(false);
        user.setCredentialsExpired(false);

        if (data.getRoles() != null) {
            List<UserRole> userRoles = Lists.newArrayList(userRoleRepository.findAll()).stream().filter(x -> data.getRoles().stream().anyMatch(name -> name.equals(x.getName())))
                    .collect(Collectors.toList());

            user.setUserRoles(userRoles);
        }
        Userdata userdata = new Userdata();
        userdata.setEmail(data.getEmail());
        userdata.setSurname(data.getSurname());
        userdata.setPosition(data.getPosition());
        userdata.setName(data.getName());
        userdata.setWorkplace(data.getWorkplace());
        userdata.setDateOfJoin(Calendar.getInstance());
        Address address = new Address();
        address.setFlatNumber(data.getFlatNumber());
        address.setBuildingNumber(data.getHouseNumber());
        address.setStreet(data.getStreet());
        address.setCity(data.getCity());
        userdata.setAddress(address);

        try {
            userdataRepository.saveAndFlush(userdata);
            user.setUserdata(userdata);
            userRepository.saveAndFlush(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
