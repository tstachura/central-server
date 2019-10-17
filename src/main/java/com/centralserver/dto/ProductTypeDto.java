package com.centralserver.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class ProductTypeDto {

    private Long id = null;
    private long version;
    private String name;
    private String manufacture;
    private long cost;
}
