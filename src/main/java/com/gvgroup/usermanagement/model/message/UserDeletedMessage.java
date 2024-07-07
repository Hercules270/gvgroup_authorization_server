package com.gvgroup.usermanagement.model.message;

import com.gvgroup.usermanagement.values.UserId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDeletedMessage {

    private String userId;

    public static UserDeletedMessage convert(UserId userId) {
        return UserDeletedMessage.builder()
                .userId(userId.toString())
                .build();
    }
}
