package com.gvgroup.usermanagement.service.command;


import com.gvgroup.usermanagement.entity.User;
import com.gvgroup.usermanagement.exception.ErrorCode;
import com.gvgroup.usermanagement.exception.UserAlreadyExistsException;
import com.gvgroup.usermanagement.repository.UserRepository;
import com.gvgroup.usermanagement.service.query.UserQueryService;
import com.gvgroup.usermanagement.values.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserQueryService userQueryService;

    @Override
    public User createUser(UserId userId, String userName, String password, String firstName, String lastName, String email, String phoneNumber, String idNumber) {
        userQueryService.findUserByUserNameOrEmail(userName, email)
                .ifPresent(user -> {
                    throw new UserAlreadyExistsException("User with username " + user.getUserName() + " already exists", "User with username " + user.getUserName() + " already exists", ErrorCode.USER_WITH_USERNAME_EXISTS);
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
                .build();
        return userRepository.save(user);
    }
}
