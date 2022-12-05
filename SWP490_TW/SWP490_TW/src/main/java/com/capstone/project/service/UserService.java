package com.capstone.project.service;

import com.capstone.project.request.UserRequest;
import com.capstone.project.response.UserResponse;

import java.util.List;

public interface UserService {
    Boolean checkEmailExist(String email);
    Boolean checkUserNameExist(String username);

    UserResponse getUserByUsername(String username);

    UserResponse getUserById(Integer id);

    List<UserResponse> getALLUser();

    UserResponse disableUser(Integer Id);

    UserResponse createUser(UserRequest request);

    UserResponse updateUser(Integer id, UserRequest request);

    UserResponse changePwd(String username,UserRequest userRequest);
    UserResponse ForgotPwd(String username);
    UserResponse createStaff(UserRequest request);
    Boolean checkPwd(String username,UserRequest request);

}
