package com.gvgroup.usermanagement.service.query;


import com.gvgroup.usermanagement.entity.Role;
import com.gvgroup.usermanagement.entity.User;
import com.gvgroup.usermanagement.values.UserId;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface UserQueryService {

    Optional<User> findUserByUserNameNullable(String username);

    Optional<User> findUserByUserNameOrEmailNullable(String userName, String email);

    Page<User> findAllUsers(int page, int size);

    User findUserByUserId(UserId userId);

    List<Role> findRolesAndAuthoritiesByUser(User user);

    List<Role> findUSerRoles(List<Long> roleIds);
}
