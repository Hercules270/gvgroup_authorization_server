package com.gvgroup.usermanagement.facade;

import com.gvgroup.usermanagement.entity.User;
import com.gvgroup.usermanagement.model.request.UpdateUserDetailsRequest;
import com.gvgroup.usermanagement.model.response.UserDetailsResponse;
import com.gvgroup.usermanagement.service.command.UserService;
import com.gvgroup.usermanagement.service.query.UserQueryService;
import com.gvgroup.usermanagement.values.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFacade {

    private final UserService userService;
    private final UserQueryService userQueryService;

    public ResponseEntity<UserDetailsResponse> updateUserDetails(UserId userId, UpdateUserDetailsRequest updateDetails) {
        userService.updateUser(userId, updateDetails);
        return getUserDetails(userId);
    }

    public ResponseEntity<UserDetailsResponse> getUserDetails(UserId userId) {
        User user = userQueryService.findUserByUserId(userId);
        user.setRoles(userQueryService.findRolesAndAuthoritiesByUser(user));
        return new ResponseEntity<>(UserDetailsResponse.toJson(user), HttpStatus.OK);
    }

}
