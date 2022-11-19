package com.capstone.project.service;

import com.capstone.project.request.UserRequest;
import com.capstone.project.response.UserResponse;

import java.util.List;

public interface UserService {
    Boolean checkEmailExist(String email);
    Boolean checkUserNameExist(String username);

    UserResponse getUserByUsername(String username);

    UserResponse getUserById(Integer id);

    List<UserResponse> getALL();

    UserResponse disableUser(Integer Id);

    UserResponse create(UserRequest request);

    UserResponse update(Integer id, UserRequest request);

    UserResponse changePwd(String username,UserRequest userRequest);
    UserResponse ForgotPwd(String username);
    UserResponse createStaff(UserRequest request);

}
