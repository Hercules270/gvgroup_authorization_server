package com.gvgroup.usermanagement.service;

import com.gvgroup.usermanagement.entity.Authority;
import com.gvgroup.usermanagement.entity.Role;
import com.gvgroup.usermanagement.exception.PermissionDoesNotExistsException;
import com.gvgroup.usermanagement.exception.ErrorCode;
import com.gvgroup.usermanagement.exception.PermissionAlreadyExistsException;
import com.gvgroup.usermanagement.model.request.AddAuthorityToRoleRequest;
import com.gvgroup.usermanagement.model.request.CreateAuthorityRequest;
import com.gvgroup.usermanagement.model.request.CreateRoleRequest;
import com.gvgroup.usermanagement.repository.AuthorityRepository;
import com.gvgroup.usermanagement.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class URMServiceImpl implements URMService {

    private final AuthorityRepository authorityRepository;
    private final RoleRepository roleRepository;
    private final URMQueryService urmQueryService;

    @Override
    public Role createRole(CreateRoleRequest createRoleRequest) {
        urmQueryService.findRoleByNameNullable(createRoleRequest.getRoleName())
                .ifPresent(role -> {
                    throw new PermissionAlreadyExistsException("Role with name " + createRoleRequest.getRoleName() + " already exists",
                            "Role with name " + createRoleRequest.getRoleName() + " already exists",
                            ErrorCode.PERMISSION_ALREADY_EXISTS);
                });

        List<Authority> authorities = Collections.emptyList();
        if(CollectionUtils.isNotEmpty(createRoleRequest.getAuthorityIds())) {
            authorities = getAuthoritiesByIds(createRoleRequest.getAuthorityIds());
        }

        Role role = Role.builder()
                .name(createRoleRequest.getRoleName())
                .authorities(authorities)
                .build();
        return roleRepository.save(role);
    }

    @Override
    public Authority createAuthority(CreateAuthorityRequest createAuthorityRequest) {
        urmQueryService.findAuthorityByNameNullable(createAuthorityRequest.getName())
                .ifPresent(authority -> {
                    throw new PermissionAlreadyExistsException("Authority with name " + createAuthorityRequest.getName() + " already exists",
                            "Authority with name " + createAuthorityRequest.getName() + " already exists",
                            ErrorCode.PERMISSION_NOT_FOUND);
                });
        Authority authority = Authority.builder()
                .name(createAuthorityRequest.getName())
                .build();
        return authorityRepository.save(authority);
    }

    @Override
    public Role addAuthority(Long roleId, AddAuthorityToRoleRequest request) {
        Role role = urmQueryService.findRoleById(roleId);
        List<Authority> authorities = getAuthoritiesByIds(request.getAuthorityIds());
        role.setAuthorities(authorities);
        return roleRepository.save(role);
    }

    private List<Authority> getAuthoritiesByIds(Set<Long> authorityIds) {
        List<Authority> authorities = urmQueryService.getAuthorities(authorityIds);
        if (authorities.size() < CollectionUtils.size(authorities)) {
            throw new PermissionDoesNotExistsException("Some authorities don't exist. Please check them",
                    "Some authorities don't exist. Please check them",
                    ErrorCode.PERMISSION_NOT_FOUND);
        }
        return authorities;
    }
}
