package com.gvgroup.usermanagement.service;

import com.gvgroup.usermanagement.entity.Authority;
import com.gvgroup.usermanagement.entity.Role;
import com.gvgroup.usermanagement.model.request.AddAuthorityToRoleRequest;
import com.gvgroup.usermanagement.model.request.CreateAuthorityRequest;
import com.gvgroup.usermanagement.model.request.CreateRoleRequest;

public interface URMService {


    Role createRole(CreateRoleRequest createRoleRequest);

    Authority createAuthority(CreateAuthorityRequest createAuthorityRequest);

    Role addAuthority(Long roleId, AddAuthorityToRoleRequest request);
}
