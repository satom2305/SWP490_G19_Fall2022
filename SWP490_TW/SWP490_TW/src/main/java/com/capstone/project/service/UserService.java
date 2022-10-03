package com.capstone.project.service;

import com.capstone.project.domain.User;
import com.capstone.project.dto.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getALL();
    UserDTO create(UserDTO userDTO);
    UserDTO getUserById(long id);
    UserDTO update(UserDTO userDTO);
    User getUserByUsername(String username);
    UserDTO changePwd(long id, String newPwd);


}
