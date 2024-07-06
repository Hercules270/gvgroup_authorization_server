package com.gvgroup.usermanagement.service;

import com.gvgroup.usermanagement.entity.Authority;
import com.gvgroup.usermanagement.entity.Role;
import com.gvgroup.usermanagement.exception.PermissionDoesNotExistsException;
import com.gvgroup.usermanagement.exception.ErrorCode;
import com.gvgroup.usermanagement.repository.AuthorityRepository;
import com.gvgroup.usermanagement.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class URMQueryServiceImpl implements URMQueryService {

    private final RoleRepository roleRepository;
    private final AuthorityRepository authorityRepository;

    @Override
    public Optional<Role> findRoleByNameNullable(String roleName) {
        return roleRepository.findByName(roleName);

    }

    @Override
    public List<Authority> getAuthorities(Set<Long> authorityIds) {
        return authorityRepository.findAllById(authorityIds);
    }

    @Override
    public Optional<Authority> findAuthorityByNameNullable(String name) {
        return authorityRepository.findByName(name);
    }

    @Override
    public Role findRoleById(Long roleId){
        return roleRepository.findById(roleId)
                .orElseThrow(() -> new PermissionDoesNotExistsException("Role with role id " + roleId + " does not exist",
                        "Role with role id " + roleId + " does not exist",
                        ErrorCode.PERMISSION_NOT_FOUND));
    }
}
