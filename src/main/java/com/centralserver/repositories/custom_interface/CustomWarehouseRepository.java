package com.centralserver.repositories.custom_interface;

import com.centralserver.model.products.Warehouse;

public interface CustomWarehouseRepository {
    void detach(Warehouse entity);
}
