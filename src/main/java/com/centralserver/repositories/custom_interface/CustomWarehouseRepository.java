package com.centralserver.repositories.custom_interface;

import com.centralserver.model.Warehouse;

public interface CustomWarehouseRepository {
    void detach(Warehouse entity);
}
