package com.gvgroup.usermanagement.model.response;


import com.gvgroup.usermanagement.entity.Authority;
import com.gvgroup.usermanagement.entity.Role;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserRoleResponse {

    private Long id;
    private String roleName;
    private List<String> authorities;


    public static List<UserRoleResponse> toJson(List<Role> roles) {
        return roles.stream()
                .map(role -> UserRoleResponse.builder()
                        .id(role.getId())
                        .roleName(role.getName())
                        .authorities(role.getAuthorities()
                                .stream()
                                .map(Authority::getName)
                                .toList())
                        .build())
                .toList();
    }

    public static UserRoleResponse toJson(Role role) {
        return toJson(List.of(role)).get(0);
    }
}
