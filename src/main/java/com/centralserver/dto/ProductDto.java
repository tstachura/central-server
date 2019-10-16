package com.centralserver.dto;

import com.centralserver.model.enums.ProductStatus;
import lombok.*;

import java.util.Calendar;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class ProductDto {

    private Long id = null;
    private long version;
    private String serialNumber;
    private ProductStatus status;
    private Long productTypeId;
    private String lastUpdate;
    private String createDate;
    private boolean deleted;
    private Long warehouseId;
}
