package com.centralserver.repositories;

import com.centralserver.model.products.Product;
import com.centralserver.model.users.User;
import com.centralserver.repositories.custom_interface.CustomProductRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, CustomProductRepository {

    Optional<Product> findBySerialNumber(@Param("serialNumber") String serialNumber);
}
