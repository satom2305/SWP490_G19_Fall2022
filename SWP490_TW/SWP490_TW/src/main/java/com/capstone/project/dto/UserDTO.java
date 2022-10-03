package com.capstone.project.dto;

import com.capstone.project.domain.Role;
import com.capstone.project.domain.UserStatus;


public class UserDTO {
    private long userID;
    private String username;
    private String password;
    private String email;
    private Role roleID;
    private UserStatus statusID;

    public UserDTO() {
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRoleID() {
        return roleID;
    }

    public void setRoleID(Role roleID) {
        this.roleID = roleID;
    }

    public UserStatus getStatusID() {
        return statusID;
    }

    public void setStatusID(UserStatus statusID) {
        this.statusID = statusID;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userID=" + userID +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", roleID=" + roleID +
                ", statusID=" + statusID +
                '}';
    }
}
