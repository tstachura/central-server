package com.centralserver.services.impl;

import com.centralserver.exception.DatabaseErrorException;
import com.centralserver.exception.EntityNotInDatabaseException;
import com.centralserver.exception.EntityOptimisticLockException;
import com.centralserver.model.products.Product;
import com.centralserver.repositories.ProductRepository;
import com.centralserver.repositories.ProductTypeRepository;
import com.centralserver.repositories.UserRepository;
import com.centralserver.repositories.WarehouseRepository;
import com.centralserver.services.ProductService;
import com.google.common.collect.Lists;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('PRODUCT_LIST_READ')")
    public List<Product> getAllProducts() {
        List<Product> products = Lists.newArrayList(productRepository.findAll());
        return products.stream().filter(product -> !product.isDeleted()).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('PRODUCT_LIST_FOR_WAREHOUSE_READ')")
    public List<Product> getAllProductsForWarehouse(Long warehouseId) {
        List<Product> products = Lists.newArrayList(productRepository.findAll()).stream()
                .filter(product -> product.getWarehouse().getId().equals(warehouseId) && !product.isDeleted()).collect(Collectors.toList());
        products.forEach(product -> Hibernate.initialize(product.getProductType()));
        return products;
    }


    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('PRODUCT_CREATE')")
    public void createNewProduct(Product product) throws DatabaseErrorException {
        try {
            productRepository.saveAndFlush(product);
        } catch (PersistenceException e) {
            throw new DatabaseErrorException(DatabaseErrorException.SERIAL_NUMBER_NAME_TAKEN);
        }
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('PRODUCT_UPDATE')")
    public void updateProduct(Product product) throws EntityNotInDatabaseException, EntityOptimisticLockException, DatabaseErrorException {
        try {
            Product oldProduct = productRepository.findById(product.getId()).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
            productRepository.detach(oldProduct);
            productRepository.saveAndFlush(product);
        } catch (ObjectOptimisticLockingFailureException e) {
            throw new EntityOptimisticLockException(EntityOptimisticLockException.OPTIMISTIC_LOCK);
        } catch (PersistenceException e) {
            throw new DatabaseErrorException(DatabaseErrorException.SERIAL_NUMBER_NAME_TAKEN);
        }
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('PRODUCT_DELETE')")
    public void deleteProductById(Long id) throws EntityNotInDatabaseException {
        productRepository.findById(id).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT)).setDeleted(true);
    }


    @Transactional(readOnly = true, propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('PRODUCT_READ')")
    public Product getProduct(Long id) throws EntityNotInDatabaseException {
        Product product = productRepository.findById(id).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        return product.isDeleted() ? null : product;
    }
}
