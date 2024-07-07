package com.gvgroup.usermanagement.model.message;


import com.gvgroup.usermanagement.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreatedMessage {

    private String userId;
    private String firstName;
    private String lastName;

    public static UserCreatedMessage convert(User user) {
        return UserCreatedMessage.builder()
                .userId(user.getUserId().toString())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }
}
