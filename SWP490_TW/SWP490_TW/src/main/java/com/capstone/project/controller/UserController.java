package com.capstone.project.controller;

import com.capstone.project.domain.User;
import com.capstone.project.dto.UserDTO;
import com.capstone.project.dto.UserRequest;
import com.capstone.project.response.ResponseObject;
import com.capstone.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @GetMapping("/users")
    public List<UserDTO> getAllUser(){
        return userService.getALL();
    }

    @PostMapping("/create")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserRequest userRequest){
        return ResponseEntity.ok(userService.create(userRequest));
    }
}
