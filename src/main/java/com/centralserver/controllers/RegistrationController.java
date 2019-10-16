package com.centralserver.controllers;

import com.centralserver.dto.user.RegistrationDto;
import com.centralserver.exception.base.SystemBaseException;
import com.centralserver.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = SystemBaseException.class)
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @RequestMapping(value = "/admin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> addUser(@RequestBody RegistrationDto data) throws SystemBaseException {
        registrationService.registerNewUserAccount(data, true);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
