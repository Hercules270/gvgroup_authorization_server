package com.gvgroup.usermanagement.service.query;


import com.gvgroup.usermanagement.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface UserQueryService {

    Optional<User> findUserByUserName(String username);

    Optional<User> findUserByUserNameOrEmail(String userName, String email);

    Page<User> findAllUsers(int page, int size);

}
