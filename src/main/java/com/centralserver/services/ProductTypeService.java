package com.centralserver.services;

import com.centralserver.exception.base.SystemBaseException;
import com.centralserver.model.products.ProductType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductTypeService {

    ProductType getProductTypeById(Long id) throws SystemBaseException;

    @Transactional(readOnly = true, propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('PRODUCT_TYPE_LIST_READ')")
    List<ProductType> getAllProductTypes();

    Long createNewDeviceModel(ProductType deviceModelAddDto) throws SystemBaseException;

    void updateProductType(ProductType deviceModelEditDto) throws SecurityException, SystemBaseException;

    void deleteDeviceModelById(Long id) throws SystemBaseException;

}
