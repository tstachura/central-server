package com.centralserver.repositories;

import com.centralserver.model.products.Product;
import com.centralserver.repositories.custom_interface.CustomProductRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, CustomProductRepository {

}
