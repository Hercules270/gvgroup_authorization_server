package com.gvgroup.usermanagement.service;

import com.gvgroup.usermanagement.entity.Authority;
import com.gvgroup.usermanagement.entity.Role;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface URMQueryService {

    Optional<Role> findRoleByNameNullable(String roleName);

    List<Authority> getAuthorities(Set<Long> authorityIds);

    Optional<Authority> findAuthorityByNameNullable(String name);

    Role findRoleById(Long roleId);
}
