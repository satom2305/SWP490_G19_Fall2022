package com.capstone.project.controller;

import com.capstone.project.response.UserResponse;
import com.capstone.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/users")
@RequiredArgsConstructor

public class UserController {

    private final UserService userService;
    @GetMapping("/")
    public ResponseEntity<List<UserResponse>> getAllAccounts() {
        return ResponseEntity.ok(userService.getALL());
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserByUsername(@PathVariable("id") String Username){
        return ResponseEntity.ok(userService.getUserByUsername(Username));
    }

}
