package com.centralserver.controllers;

import com.centralserver.exception.base.SystemBaseException;
import com.centralserver.model.products.ProductType;
import com.centralserver.services.impl.ProductTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products/type")
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = SystemBaseException.class)
public class ProductTypeController {

    @Autowired
    private ProductTypeServiceImpl productTypeService;


    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    ProductType get(@PathVariable Long id) throws SystemBaseException {
        return productTypeService.getProductTypeById(id);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<ProductType> getAll() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return productTypeService.getAllProductTypes();
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public Long create(@RequestBody ProductType productType) throws SystemBaseException {
        return productTypeService.createNewDeviceModel(productType);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void update(@RequestBody ProductType productType) throws SystemBaseException {
        productTypeService.updateProductType(productType);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) throws SystemBaseException {
        productTypeService.deleteDeviceModelById(id);
    }
}
