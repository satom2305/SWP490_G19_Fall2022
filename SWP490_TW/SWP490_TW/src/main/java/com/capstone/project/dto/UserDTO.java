package com.capstone.project.dto;

import com.capstone.project.domain.Role;
import com.capstone.project.domain.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserDTO {
    private int userId;
    private String username;
    private String password;
    private String email;
    private Role role;
    private UserStatus status;
    @Override
    public String toString() {
        return "UserDTO{" +
                "userID=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", roleID=" + role +
                ", statusID=" + status +
                '}';
    }
}
