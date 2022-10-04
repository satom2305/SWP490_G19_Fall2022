package com.capstone.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRequest {

    private String username;
    private String password;
    private String email;
    private int roleId;
    private int statusId;


}
