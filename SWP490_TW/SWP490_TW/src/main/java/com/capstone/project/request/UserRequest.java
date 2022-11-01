package com.capstone.project.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRequest {
    private Integer userId;
    private String username;
    private String password;
    private String email;
    private Boolean status;

}
