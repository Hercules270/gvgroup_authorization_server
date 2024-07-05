package com.gvgroup.usermanagement.model.response;


import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class AbstractPageableResponse {

    private int totalPages;
    private long totalElements;

}
