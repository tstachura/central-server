package com.centralserver.services.impl;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.stream.Collectors;

import com.centralserver.dto.ProductDto;
import com.centralserver.dto.converter.ProductConverter;
import com.centralserver.exception.DatabaseErrorException;
import com.centralserver.exception.EntityNotInDatabaseException;
import com.centralserver.exception.EntityOptimisticLockException;
import com.centralserver.kafka.message.KafkaSyncMessage;
import com.centralserver.model.products.Product;
import com.centralserver.model.products.ProductType;
import com.centralserver.model.products.Warehouse;
import com.centralserver.repositories.ProductRepository;
import com.centralserver.repositories.ProductTypeRepository;
import com.centralserver.repositories.WarehouseRepository;
import com.centralserver.services.ProductService;
import com.google.common.collect.Lists;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Autowired
    private KafkaTemplate<String, KafkaSyncMessage> kafkaTemplate;

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
    public void createNewProduct(ProductDto productDto) throws DatabaseErrorException {
        try {
            Product product = new Product();
            Warehouse warehouse = warehouseRepository.getOne(productDto.getWarehouseId());
            ProductType productType = productTypeRepository.getOne(productDto.getProductTypeId());
            productRepository.saveAndFlush(ProductConverter.toProduct(productDto, product, warehouse, productType));
        } catch (PersistenceException e) {
            throw new DatabaseErrorException(DatabaseErrorException.SERIAL_NUMBER_NAME_TAKEN);
        }
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('PRODUCT_UPDATE')")
    public void updateProduct(ProductDto product) throws EntityNotInDatabaseException, EntityOptimisticLockException, DatabaseErrorException {
        try {
            Product oldProduct = productRepository.findById(product.getId()).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
            productRepository.detach(oldProduct);
            Warehouse warehouse = warehouseRepository.getOne(product.getWarehouseId());
            ProductType productType = productTypeRepository.getOne(product.getProductTypeId());
            productRepository.saveAndFlush(ProductConverter.toProduct(product, oldProduct, warehouse, productType));
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
        Product product = productRepository.findById(id).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        product.setDeleted(true);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('PRODUCT_READ')")
    public Product getProduct(Long id) throws EntityNotInDatabaseException {
        Product product = productRepository.findById(id).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        Hibernate.initialize(product.getWarehouse());
        Hibernate.initialize(product.getProductType());
        return product.isDeleted() ? null : product;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('PRODUCT_READ')")
    public Product getProductBySerialNumber(String serialNumber) throws EntityNotInDatabaseException {
        Product product = productRepository.findBySerialNumber(serialNumber).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        return product.isDeleted() ? null : product;
    }
}
