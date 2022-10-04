package com.capstone.project.service;

import com.capstone.project.domain.User;
import com.capstone.project.dto.UserDTO;
import com.capstone.project.dto.UserRequest;

import java.util.List;

public interface UserService {
    List<UserDTO> getALL();
    UserDTO create(UserRequest userRequest);
    UserDTO getUserById(long id);

    UserDTO update(UserDTO userDTO);
    User getUserByUsername(String username);
    UserDTO changePwd(long id, String newPwd);


}
