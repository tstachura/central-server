package com.centralserver.controllers;

import com.centralserver.dto.user.UserEditDto;
import com.centralserver.exception.base.SystemBaseException;
import com.centralserver.services.AccountEditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account/edit")
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = SystemBaseException.class)
public class AccountEditController {


    @Autowired
    private AccountEditService accountEditService;

    @RequestMapping(value = "/admin", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> saveAccountAfterEdit(@RequestBody UserEditDto data) throws SystemBaseException {
        accountEditService.updateAccountbyAdmin(data);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
