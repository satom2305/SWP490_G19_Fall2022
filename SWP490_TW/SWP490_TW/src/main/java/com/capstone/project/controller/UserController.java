package com.capstone.project.controller;

import com.capstone.project.response.ResponseObject;
import com.capstone.project.response.UserResponse;
import com.capstone.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/users")
@RequiredArgsConstructor

public class UserController {

    private final UserService userService;
    @GetMapping("")
    public ResponseEntity<?> getAllAccounts() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Successfully",true,userService.getALL()));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserByUsername(@PathVariable("id") String Username){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Successfully",true,userService.getUserByUsername(Username)));
    }

}
