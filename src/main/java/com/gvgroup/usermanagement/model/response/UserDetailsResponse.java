package com.gvgroup.usermanagement.model.response;

import com.gvgroup.usermanagement.entity.User;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class UserDetailsResponse {

    private UUID userId;

    private String userName;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String idNumber;

    private String email;

    private Boolean isActive;

    private List<UserRoleResponse> roles;


    public static UserDetailsResponse toJson(User user) {
        return UserDetailsResponse.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .idNumber(user.getIdNumber())
                .email(user.getEmail())
                .isActive(user.getIsActive())
                .roles(UserRoleResponse.toJson(user.getRoles()))
                .build();
    }
}
