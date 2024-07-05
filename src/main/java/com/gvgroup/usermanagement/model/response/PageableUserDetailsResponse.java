package com.gvgroup.usermanagement.model.response;


import com.gvgroup.usermanagement.entity.Role;
import com.gvgroup.usermanagement.entity.User;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@SuperBuilder
public class PageableUserDetailsResponse extends AbstractPageableResponse{

    private List<UserDetailsResponse> users;


    public static PageableUserDetailsResponse toJson(Page<User> users) {
        return PageableUserDetailsResponse.builder()
                .users(users.getContent().stream()
                        .map(UserDetailsResponse::toJson)
                        .collect(Collectors.toList()))
                .totalPages(users.getTotalPages())
                .totalElements(users.getTotalElements())
                .build();
    }

    @Data
    @Builder
    public static class UserDetailsResponse {

        private UUID userId;

        private String userName;

        private String firstName;

        private String lastName;

        private String phoneNumber;

        private String idNumber;

        private String email;

        private Boolean isActive;

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
                    .build();
        }
    }
}
