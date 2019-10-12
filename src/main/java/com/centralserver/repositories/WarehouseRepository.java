package com.centralserver.repositories;

import com.centralserver.model.Warehouse;
import com.centralserver.repositories.custom_interface.CustomWarehouseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long>, CustomWarehouseRepository {

}
