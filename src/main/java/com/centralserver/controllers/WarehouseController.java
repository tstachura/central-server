package com.centralserver.controllers;

import com.centralserver.exception.base.SystemBaseException;
import com.centralserver.model.Warehouse;
import com.centralserver.services.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/warehouses")
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = SystemBaseException.class)
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    Warehouse getWarehouseToView(@PathVariable Long id) throws SystemBaseException {
        return warehouseService.getWarehouse(id);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<Warehouse> getAll() {
        return warehouseService.getAll();
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> create(@RequestBody Warehouse warehouse) throws SystemBaseException {
        warehouseService.createWarehouse(warehouse);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void update(@RequestBody Warehouse warehouse) throws SystemBaseException {
        warehouseService.updateWarehouse(warehouse);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) throws SystemBaseException {
        warehouseService.deleteWarehouseById(id);
    }
}
