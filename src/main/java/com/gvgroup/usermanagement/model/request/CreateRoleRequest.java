package com.gvgroup.usermanagement.model.request;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Set;

@Data
public class CreateRoleRequest {

    @NotBlank(message = "role_name should not be empty")
    private String roleName;
    private Set<Long> authorityIds;

}
