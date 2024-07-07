package com.gvgroup.usermanagement.controller;


import com.gvgroup.usermanagement.facade.UserFacade;
import com.gvgroup.usermanagement.model.request.UpdateUserDetailsRequest;
import com.gvgroup.usermanagement.model.response.UserDetailsResponse;
import com.gvgroup.usermanagement.values.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserFacade userFacade;

    @PreAuthorize(value = "isAuthenticated()")
    @GetMapping("/me")
    public ResponseEntity<UserDetailsResponse> getDetails(@AuthenticationPrincipal Jwt authentication) {
        return userFacade.getUserDetails(UserId.from(authentication.getClaimAsString("user_id")));
    }

    @PreAuthorize(value = "isAuthenticated()")
    @PutMapping("/me")
    public ResponseEntity<UserDetailsResponse> updateUser(@RequestBody UpdateUserDetailsRequest request,
                                                        @AuthenticationPrincipal Jwt authentication) {
        return userFacade.updateUserDetails(UserId.from(authentication.getClaimAsString("user_id")), request);
    }
}
