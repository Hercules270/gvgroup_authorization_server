package com.gvgroup.usermanagement.model.request;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateUserRequest {

    @NotBlank(message = "user_name should not be empty")
    private String userName;

    private String password;

    @NotBlank(message = "first_name should not be empty")
    private String firstName;

    @NotBlank(message = "last_name should not be empty")
    private String lastName;

    private String phoneNumber;

    private String idNumber;

    private String email;

}
