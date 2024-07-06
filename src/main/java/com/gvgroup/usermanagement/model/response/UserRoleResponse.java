package com.gvgroup.usermanagement.model.response;


import com.gvgroup.usermanagement.entity.Authority;
import com.gvgroup.usermanagement.entity.Role;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserRoleResponse {

    private String roleName;
    private List<String> authorities;


    public static List<UserRoleResponse> toJson(List<Role> roles) {
        return roles.stream()
                .map(role -> UserRoleResponse.builder()
                        .roleName(role.getName())
                        .authorities(role.getAuthorities()
                                .stream()
                                .map(Authority::getName)
                                .toList())
                        .build())
                .toList();
    }
}
