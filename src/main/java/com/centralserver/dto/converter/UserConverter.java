package com.centralserver.dto.converter;

import com.centralserver.dto.user.UserEditDto;
import com.centralserver.model.users.User;
import com.centralserver.model.users.UserRole;

import java.util.stream.Collectors;

public class UserConverter {

    public static UserEditDto toUserEditDto(User user) {
        return UserEditDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .position(user.getUserdata().getPosition())
                .name(user.getUserdata().getName())
                .surname(user.getUserdata().getSurname())
                .city(user.getUserdata().getAddress().getCity())
                .email(user.getUserdata().getEmail())
                .street(user.getUserdata().getAddress().getStreet())
                .houseNumber(user.getUserdata().getAddress().getBuildingNumber())
                .flatNumber(user.getUserdata().getAddress().getFlatNumber())
                .roles(user.getUserRoles().stream().map(UserRole::getName).collect(Collectors.toList()))
                .workplace(user.getUserdata().getWorkplace())
                .position(user.getUserdata().getPosition())
                .versionUser(user.getVersion())
                .versionUserdata(user.getUserdata().getVersion())
                .build();
    }
}
