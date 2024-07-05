package com.gvgroup.usermanagement.service.command;


import com.gvgroup.usermanagement.entity.User;
import com.gvgroup.usermanagement.values.UserId;

public interface UserService {
    User createUser(UserId userId, String userName, String password, String firstName, String lastName, String email, String phoneNumber, String idNumber);
}
