package com.capstone.project.controller;

import com.capstone.project.request.UserRequest;
import com.capstone.project.response.ResponseObject;
import com.capstone.project.response.UserResponse;
import com.capstone.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor

public class UserController {

    @Autowired
    private final UserService userService;
    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getAllAccounts() {
        return ResponseEntity.ok(userService.getALL());
    }

//    @PostMapping("/create")
//    public ResponseEntity<UserDTO> createUser(@RequestBody UserRequest userRequest){
//        return ResponseEntity.ok(userService.create(userRequest));
//    }
}
