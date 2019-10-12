package com.centralserver.repositories;

import com.centralserver.model.products.ProductType;
import com.centralserver.repositories.custom_interface.CustomProductTypeRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType, Long>, CustomProductTypeRepository {

}
