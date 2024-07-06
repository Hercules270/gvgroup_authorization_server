package com.gvgroup.usermanagement.controller;


import com.gvgroup.usermanagement.facade.UserManagementFacade;
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
import com.gvgroup.usermanagement.values.UserId;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminUserManagementController {

    private final UserManagementFacade userManagementFacade;

    public AdminUserManagementController(UserManagementFacade userManagementFacade) {
        this.userManagementFacade = userManagementFacade;
    }

    @PostMapping("/user")
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody @Valid CreateUserRequest request) {
        return userManagementFacade.createUser(request);
    }

    @PreAuthorize(value = "hasAnyAuthority('VIEW_USERS')")
    @GetMapping("/user")
    public ResponseEntity<PageableUserDetailsResponse> getUsers(@RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "10") int size) {
        return userManagementFacade.getUsers(page, size);
    }

    @PreAuthorize(value = "hasAnyAuthority('VIEW_USERS')")
    @GetMapping("/user/{user_id}")
    public ResponseEntity<UserDetailsResponse> getUserDetails(@PathVariable("user_id") String userId) {
        return userManagementFacade.getUserDetails(UserId.from(userId));
    }

    @PreAuthorize(value = "hasAnyAuthority('VIEW_USERS')")
    @PutMapping("/user/{user_id}")
    public ResponseEntity<UserDetailsResponse> getUserDetails(@PathVariable("user_id") String userId, @RequestBody UpdateUserDetailsRequest updateUserDetailsRequest) {
        return userManagementFacade.updateUserDetails(UserId.from(userId), updateUserDetailsRequest);
    }

    @PreAuthorize(value = "hasAnyAuthority('VIEW_USERS')")
    @DeleteMapping("/user/{user_id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("user_id") String userId) {
        return userManagementFacade.deleteUser(UserId.from(userId));
    }

    @PreAuthorize(value = "hasAnyAuthority('VIEW_USERS')")
    @PostMapping("/role")
    public ResponseEntity<UserRoleResponse> createRole(@RequestBody @Valid CreateRoleRequest createRoleRequest) {
        return userManagementFacade.createRole(createRoleRequest);
    }

    @PreAuthorize(value = "hasAnyAuthority('VIEW_USERS')")
    @PutMapping("/role/{roleId}")
    public ResponseEntity<UserRoleResponse> addAuthorityToRole(@PathVariable("roleId") Long roleName, @RequestBody @Valid AddAuthorityToRoleRequest request) {
        return userManagementFacade.addAuthorityToRole(roleName, request);
    }

    @PreAuthorize(value = "hasAnyAuthority('VIEW_USERS')")
    @PostMapping("/authority")
    public ResponseEntity<AuthorityResponse> createAuthority(@RequestBody @Valid CreateAuthorityRequest createAuthorityRequest) {
        return userManagementFacade.createAuthority(createAuthorityRequest);
    }
}
