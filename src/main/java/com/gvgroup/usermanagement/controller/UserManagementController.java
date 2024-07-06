package com.gvgroup.usermanagement.controller;


import com.gvgroup.usermanagement.facade.UserManagementFacade;
import com.gvgroup.usermanagement.model.request.CreateUserRequest;
import com.gvgroup.usermanagement.model.response.CreateUserResponse;
import com.gvgroup.usermanagement.model.response.PageableUserDetailsResponse;
import com.gvgroup.usermanagement.model.response.UserDetailsResponse;
import com.gvgroup.usermanagement.values.UserId;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserManagementController {

    private final UserManagementFacade userManagementFacade;

    public UserManagementController(UserManagementFacade userManagementFacade) {
        this.userManagementFacade = userManagementFacade;
    }

    @PostMapping("/user")
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest request) {
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


}
