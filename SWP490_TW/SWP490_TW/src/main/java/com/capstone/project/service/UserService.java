package com.capstone.project.service;

import com.capstone.project.request.UserRequest;
import com.capstone.project.response.UserResponse;

import java.util.List;

public interface UserService {


    UserResponse getUserByUsername(String username);
    UserResponse getUserById(String username);

    List<UserResponse> getALL();
    UserResponse disableUser(String username);

    UserResponse create(UserRequest request);

    UserResponse update(String username, UserRequest request);
    UserResponse changePwd(long id, String newPwd);
    UserResponse delete(int id, String newPwd);
}
