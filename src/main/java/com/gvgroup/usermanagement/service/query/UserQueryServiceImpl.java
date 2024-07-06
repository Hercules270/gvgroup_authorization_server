package com.gvgroup.usermanagement.service.query;


import com.gvgroup.usermanagement.entity.Role;
import com.gvgroup.usermanagement.entity.User;
import com.gvgroup.usermanagement.exception.ErrorCode;
import com.gvgroup.usermanagement.exception.UserNotFoundException;
import com.gvgroup.usermanagement.repository.RoleRepository;
import com.gvgroup.usermanagement.repository.UserRepository;
import com.gvgroup.usermanagement.values.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public Optional<User> findUserByUserNameNullable(String username) {
        return userRepository.findByUserName(username);
    }

    @Override
    public Optional<User> findUserByUserNameOrEmailNullable(String userName, String email) {
        return userRepository.findByUserNameOrEmail(userName, email);
    }

    @Override
    public Page<User> findAllUsers(int page, int size) {
        return userRepository.findAll(PageRequest.of(page, size));

    }

    @Override
    public User findUserByUserId(UserId userId) {
        return userRepository.findByUserId(userId.getId())
                .orElseThrow(() -> new UserNotFoundException("User with user id " + userId + " does not exist",
                        "User with user id " + userId + " does not exist",
                        ErrorCode.USER_WITH_USER_ID_NOT_FOUND));
    }

    @Override
    public List<Role> findRolesAndAuthoritiesByUser(User user) {
        return roleRepository.fetchRolesWithAuthorities(user.getRoles().stream().map(Role::getId).toList());
    }

    @Override
    public List<Role> findUSerRoles(List<Long> roleIds) {
        return roleRepository.findAllById(roleIds);
    }

}
