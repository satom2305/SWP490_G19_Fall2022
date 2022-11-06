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
    @GetMapping("/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable("username") String username){
        boolean checkUsernameExist = userService.checkUserNameExist(username);
        if(checkUsernameExist == false){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok","Username is not Exist",false,"null"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Successfully",true,userService.getUserByUsername(username)));
    }

    @GetMapping("/userId/{id}")
    public ResponseEntity<?> getUserByUserId(@PathVariable("id") Integer id){
        UserResponse checkUsernameExist = userService.getUserById(id);
        if(checkUsernameExist == null){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok","Username is not Exist",false,"null"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Successfully",true,userService.getUserById(id)));
    }

    @PutMapping("/disable/{id}")
    public ResponseEntity<?> disableUser(@PathVariable("id")Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Successfully",true,userService.disableUser(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Integer id, @RequestBody UserRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Successfully",true,userService.update(id,request)));
    }

    @PostMapping()
    public ResponseEntity<?> createUser(@RequestBody UserRequest request){
        System.out.println(request);
        UserResponse userResponse = userService.create(request);
        if(userResponse == null){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok","Error",false,"null"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Successfully",true,userResponse));
    }

    @PutMapping("/changePwd/{username}")
    public ResponseEntity<?> changePwd(@PathVariable("username") String username,@RequestBody String newPwd){
        UserResponse userResponse = userService.changePwd(username,newPwd);
        if(userResponse == null){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok","Error",false,"null"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Successfully",true,userResponse));
    }

}
