package com.gvgroup.usermanagement.service.command;


import com.gvgroup.usermanagement.entity.Role;
import com.gvgroup.usermanagement.entity.User;
import com.gvgroup.usermanagement.exception.ErrorCode;
import com.gvgroup.usermanagement.exception.UserAlreadyExistsException;
import com.gvgroup.usermanagement.model.request.UpdateUserDetailsRequest;
import com.gvgroup.usermanagement.repository.UserRepository;
import com.gvgroup.usermanagement.service.query.UserQueryService;
import com.gvgroup.usermanagement.values.UserId;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserQueryService userQueryService;

    @Override
    public User createUser(UserId userId, String userName, String password, String firstName, String lastName, String email, String phoneNumber, String idNumber) {
        userQueryService.findUserByUserNameOrEmailNullable(userName, email)
                .ifPresent(user -> {
                    throw new UserAlreadyExistsException("User with username " + user.getUserName() + " or email + " + user.getEmail() + " already exists", "User with username " + user.getUserName() + " already exists", ErrorCode.USER_WITH_USERNAME_EXISTS);
                });
        User user = User.builder()
                .userId(userId.getId())
                .userName(userName)
                .password(password)
                .firstName(firstName)
                .lastName(lastName)
                .phoneNumber(phoneNumber)
                .idNumber(idNumber)
                .email(email)
                .isActive(Boolean.TRUE)
                .createDate(Instant.now())
                .build();
        return userRepository.save(user);
    }

    @Override
    public User updateUser(UserId userId, UpdateUserDetailsRequest updateDetails) {
        User user = userQueryService.findUserByUserId(userId);
        if(StringUtils.isNoneEmpty(updateDetails.getFirstName())) {
            user.setFirstName(updateDetails.getFirstName());
        }
        if (StringUtils.isNoneEmpty(updateDetails.getLastName())) {
            user.setLastName(updateDetails.getLastName());
        }
        if (StringUtils.isNoneEmpty(updateDetails.getPhoneNumber())) {
            user.setPhoneNumber(updateDetails.getPhoneNumber());
        }
        if (StringUtils.isNoneEmpty(updateDetails.getIdNumber())) {
            user.setIdNumber(updateDetails.getIdNumber());
        }
        if(CollectionUtils.isNotEmpty(updateDetails.getRoleIds())) {
            List<Role> roles = userQueryService.findUSerRoles(updateDetails.getRoleIds());
            user.setRoles(roles);
        }
        user.setUpdateDate(Instant.now());
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(UserId userId) {
        userRepository.deleteByUserId(userId.getId());
    }
}
