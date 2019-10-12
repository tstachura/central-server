package com.centralserver.dto.user;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class PasswordInfoForAdmin implements Serializable {

    private Long id;
    private String newPassword;
    private long userVersion;
}
