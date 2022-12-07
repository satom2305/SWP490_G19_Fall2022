package com.capstone.project.response;

import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserResponse {
    private int userId;
    private String username;
    private String password;
    private String email;
    private Boolean status;
    private Set role;
}
