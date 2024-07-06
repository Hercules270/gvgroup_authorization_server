package com.gvgroup.usermanagement.model.request;

import lombok.Data;

import java.util.List;

@Data
public class UpdateUserDetailsRequest {

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String idNumber;

    private List<Long> roleIds;

}
