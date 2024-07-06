package com.gvgroup.usermanagement.model.response;


import com.gvgroup.usermanagement.entity.User;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.Page;

import java.util.UUID;
import java.util.stream.Collectors;

@Data
@SuperBuilder
public class PageableUserDetailsResponse extends AbstractPageableResponse<PageableUserDetailsResponse.CompactUserDetails>{

    public static PageableUserDetailsResponse toJson(Page<User> users) {
        return PageableUserDetailsResponse.builder()
                .elements(users.getContent().stream()
                        .map(CompactUserDetails::toJson)
                        .collect(Collectors.toList()))
                .totalPages(users.getTotalPages())
                .totalElements(users.getTotalElements())
                .build();
    }

    @Data
    @Builder
    public static class CompactUserDetails {

        private UUID userId;

        private String userName;

        private String firstName;

        private String lastName;

        private String phoneNumber;

        private String idNumber;

        private String email;

        private Boolean isActive;

        public static CompactUserDetails toJson(User user) {
            return CompactUserDetails.builder()
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
