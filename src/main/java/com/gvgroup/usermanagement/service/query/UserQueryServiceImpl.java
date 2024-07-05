package com.gvgroup.usermanagement.service.query;


import com.gvgroup.usermanagement.entity.User;
import com.gvgroup.usermanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;

    public Optional<User> findUserByUserName(String username) {
        return userRepository.findByUserName(username);
    }

    @Override
    public Optional<User> findUserByUserNameOrEmail(String userName, String email) {
        return userRepository.findUserByUserNameOrEmail(userName, email);
    }

    @Override
    public Page<User> findAllUsers(int page, int size) {
        return userRepository.findAll(PageRequest.of(page, size));

    }

}
