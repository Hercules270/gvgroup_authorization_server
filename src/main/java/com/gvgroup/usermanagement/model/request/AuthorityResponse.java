package com.gvgroup.usermanagement.model.request;


import com.gvgroup.usermanagement.entity.Authority;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorityResponse {

    private Long id;
    private String authorityName;

    public static AuthorityResponse toJson(Authority authority) {
        return AuthorityResponse.builder()
                .id(authority.getId())
                .authorityName(authority.getName())
                .build();
    }
}
