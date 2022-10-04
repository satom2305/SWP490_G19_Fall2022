package com.capstone.project.mapper;

import com.capstone.project.domain.User;
import com.capstone.project.dto.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public static UserDTO convertToUserDTO(User user){

        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole());
        userDTO.setStatus(user.getStatus());

        return userDTO;
    }
}
