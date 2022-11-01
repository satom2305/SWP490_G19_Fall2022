package com.capstone.project.service;

import com.capstone.project.request.UserRequest;
import com.capstone.project.response.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse getUserByUsername(String username);

    UserResponse getUserById(Integer id);

    List<UserResponse> getALL();

    UserResponse disableUser(String username);

    UserResponse create(UserRequest request);

    UserResponse update(Integer id, UserRequest request);

    UserResponse changePwd(long id, String newPwd);

    UserResponse delete(int id, String newPwd);
}
