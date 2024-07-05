package com.gvgroup.usermanagement.model.request;


import lombok.Data;

@Data
public class CreateUserRequest {

    private String userName;

    private String password;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String idNumber;

    private String email;

}
