package com.gvgroup.usermanagement.model.request;


import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.Set;

@Data
public class AddAuthorityToRoleRequest {

    @NotEmpty(message = "authority_ids should not be empty.")
    private Set<Long> authorityIds;
}
