package com.capstone.project.controller;

import com.capstone.project.request.UserRequest;
import com.capstone.project.response.ResponseObject;
import com.capstone.project.response.UserResponse;
import com.capstone.project.service.EmailService;
import com.capstone.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/users")
@RequiredArgsConstructor

public class UserController {

    private final UserService userService;
    private final EmailService emailService;

    @GetMapping("")
    public ResponseEntity<?> getAllAccounts() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully", true, userService.getALLUser()));
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable("username") String username) {
        boolean checkUsernameExist = userService.checkUserNameExist(username);
        if (checkUsernameExist == false) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Username is not Exist", false, "null"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully", true, userService.getUserByUsername(username)));
    }

    @GetMapping("/userId/{id}")
    public ResponseEntity<?> getUserByUserId(@PathVariable("id") Integer id) {
        UserResponse checkUsernameExist = userService.getUserById(id);
        if (checkUsernameExist == null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Username is not Exist", false, "null"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully", true, userService.getUserById(id)));
    }

    @PutMapping("/disable/{id}")
    public ResponseEntity<?> disableUser(@PathVariable("id") Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully", true, userService.disableUser(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Integer id, @RequestBody UserRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully", true, userService.updateUser(id, request)));
    }

    @PostMapping()
    public ResponseEntity<?> createUser(@RequestBody UserRequest request) {
        Integer check = userService.createUser(request);
        if (check == 1) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Email này đã tồn tại", false, "null"));
        } else if(check == 2){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Username đã tồn tại", false, "null"));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Tạo user thành công", true, "null"));
        }
    }

    @PostMapping("/staff")
    public ResponseEntity<?> createStaff(@RequestBody UserRequest request) {
        UserResponse userResponse = userService.createStaff(request);
        if (userResponse == null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Error", false, "null"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully", true, userResponse));
    }

    @PutMapping("/changePwd/{oldPassword}&{newPassword}")
    public ResponseEntity<?> changePwd(@PathVariable("oldPassword") String oldPassword, @PathVariable("oldPassword") String newPassword) {
        Integer check = userService.changePwd(oldPassword, newPassword);
        if (check == 0) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Đổi thành công", true, "null"));
        }else if (check == 1){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Sai mật khẩu cũ", false, "null"));
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Mật khẩu mới trùng với mật khẩu cũ", false, "null"));
        }
    }

    @GetMapping("/checkPwd/{username}")
    public ResponseEntity<?> checkPwd(@PathVariable("username") String username,@RequestBody UserRequest request){
        boolean result = userService.checkPwd(username,request);
        if(result){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Mat khau trung voi mat khau cu", true, "null"));
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Mat khau Khong trung voi mat khau cu", false, "null"));
        }
    }

    @PostMapping("/forgetPassword")
    public ResponseEntity<?> sendPwdToEmail(@RequestBody UserRequest request) {
        if (request == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("fail", "Send mail fail", false, "null")
            );
        }
        try {
            boolean checkUsernameExist = userService.checkUserNameExist(request.getUsername());
            if (!checkUsernameExist) {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "User not found", false, "null"));
            }
            emailService.sendMailForgetPass(request);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "successfully", true, request.getUsername())
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("fail", "mail not exist", false, "null")
            );
        }
    }

    @GetMapping("/searchUserByUsername/{username}")
    public ResponseEntity<?> searchUserByUsername(@PathVariable("username") String username) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully", true, userService.searchUserByUsername(username))
        );
    }

}
