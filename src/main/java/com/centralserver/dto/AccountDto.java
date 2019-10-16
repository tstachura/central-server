package com.centralserver.dto.user;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class UserEditDto implements Serializable {

    private Long id;
    private String username;
    private String email;
    private String name;
    private String surname;
    private String city;
    private String street;
    private String houseNumber;
    private String flatNumber;
    private String position;
    private String workplace;
    private Long officeId;
    private List<String> roles;
    private long versionUser;
    private long versionUserdata;
    private long versionWarehouse;
    private String officeName;
    private String language;
}
