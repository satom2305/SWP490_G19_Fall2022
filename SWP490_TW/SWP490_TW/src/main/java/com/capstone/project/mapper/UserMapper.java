package com.capstone.project.mapper;

import com.capstone.project.domain.User;
import com.capstone.project.dto.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public static UserDTO convertToUserDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setUserID(user.getUserID());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setEmail(user.getEmail());
        userDTO.setRoleID(user.getRoleID());
        userDTO.setStatusID(user.getStatusID());

        return userDTO;
    }
}
