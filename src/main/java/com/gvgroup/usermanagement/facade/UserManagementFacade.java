package com.gvgroup.usermanagement.facade;


import com.gvgroup.usermanagement.entity.User;
import com.gvgroup.usermanagement.model.request.CreateUserRequest;
import com.gvgroup.usermanagement.model.response.CreateUserResponse;
import com.gvgroup.usermanagement.model.response.PageableUserDetailsResponse;
import com.gvgroup.usermanagement.model.response.UserDetailsResponse;
import com.gvgroup.usermanagement.service.command.UserService;
import com.gvgroup.usermanagement.service.query.UserQueryService;
import com.gvgroup.usermanagement.values.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserManagementFacade {

    private final UserQueryService userQueryService;

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<CreateUserResponse> createUser(CreateUserRequest request) {
        UserId userId = UserId.create();
        userService.createUser(
                userId,
                request.getUserName(),
                passwordEncoder.encode(request.getPassword()),
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPhoneNumber(),
                request.getIdNumber());
        return new ResponseEntity<>(new CreateUserResponse(userId.getId()), HttpStatus.CREATED);
    }

    public ResponseEntity<PageableUserDetailsResponse> getUsers(int page, int size) {
        PageableUserDetailsResponse response = PageableUserDetailsResponse.toJson(userQueryService.findAllUsers(page, size));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<UserDetailsResponse> getUserDetails(UserId userId) {
        User user = userQueryService.findUserByUserId(userId);
        user.setRoles(userQueryService.findRolesAndAuthoritiesByUser(user));
        return new ResponseEntity<>(UserDetailsResponse.toJson(user), HttpStatus.OK);
    }
}
