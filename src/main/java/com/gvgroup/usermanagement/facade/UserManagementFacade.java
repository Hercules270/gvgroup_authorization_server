package com.gvgroup.usermanagement.facade;


import com.gvgroup.usermanagement.entity.Authority;
import com.gvgroup.usermanagement.entity.Role;
import com.gvgroup.usermanagement.entity.User;
import com.gvgroup.usermanagement.model.request.AddAuthorityToRoleRequest;
import com.gvgroup.usermanagement.model.request.AuthorityResponse;
import com.gvgroup.usermanagement.model.request.CreateAuthorityRequest;
import com.gvgroup.usermanagement.model.request.CreateRoleRequest;
import com.gvgroup.usermanagement.model.request.CreateUserRequest;
import com.gvgroup.usermanagement.model.request.UpdateUserDetailsRequest;
import com.gvgroup.usermanagement.model.response.CreateUserResponse;
import com.gvgroup.usermanagement.model.response.PageableUserDetailsResponse;
import com.gvgroup.usermanagement.model.response.UserDetailsResponse;
import com.gvgroup.usermanagement.model.response.UserRoleResponse;
import com.gvgroup.usermanagement.service.URMService;
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

    private final URMService urmService;

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

    public ResponseEntity<UserDetailsResponse> updateUserDetails(UserId userId, UpdateUserDetailsRequest updateDetails) {
        userService.updateUser(userId, updateDetails);
        return getUserDetails(userId);
    }

    public ResponseEntity<UserRoleResponse> createRole(CreateRoleRequest createRoleRequest) {
        Role role = urmService.createRole(createRoleRequest);
        return new ResponseEntity<>(UserRoleResponse.toJson(role), HttpStatus.CREATED);
    }

    public ResponseEntity<AuthorityResponse> createAuthority(CreateAuthorityRequest createAuthorityRequest) {
        Authority authority = urmService.createAuthority(createAuthorityRequest);
        return new ResponseEntity<>(AuthorityResponse.toJson(authority), HttpStatus.CREATED);
    }

    public ResponseEntity<UserRoleResponse> addAuthorityToRole(Long roleId, AddAuthorityToRoleRequest request) {
        Role role = urmService.addAuthority(roleId, request);
        return new ResponseEntity<>(UserRoleResponse.toJson(role), HttpStatus.OK);
    }
}
