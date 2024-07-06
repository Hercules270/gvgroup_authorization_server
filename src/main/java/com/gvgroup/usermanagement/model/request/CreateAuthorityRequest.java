package com.gvgroup.usermanagement.model.request;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateAuthorityRequest {

    @NotBlank(message = "name should not be blank")
    private String name;

}
