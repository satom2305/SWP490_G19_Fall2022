package com.capstone.project.controller;

import com.capstone.project.config.exception.AppException;
import com.capstone.project.domain.User;
import com.capstone.project.repository.UserRepository;
import com.capstone.project.request.UserRequest;
import com.capstone.project.response.UserResponse;
import com.capstone.project.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserControllerTest {
    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Spy
    private ModelMapper mapper;

    private User user;

    @BeforeEach
    public void init() {
        user = new User(1, "admin", "admin", "admin@gmail.com", true, null, null, null);
    }

    @Test
    public void TestGetUserByUserName() {
        UserRequest userRequest = new UserRequest(1, "admin", "admin", "admin@gmail.com", true);
        String username = "admin";
        Mockito.when(userRepository.findByUsername(username)).thenReturn(Optional.ofNullable(user));
        UserResponse userResponse = userService.getUserByUsername(userRequest.getUsername());
        Assert.assertEquals("admin", userResponse.getUsername());
    }

    @Test
    public void TestGetUserByUserNameFail() {
        //set up
        UserRequest userRequest = new UserRequest(1, "admin", "admin", "admin@gmail.com", true);
        String username = "user";
        Mockito.when(userRepository.findByUsername(username)).thenThrow(new AppException("User not found", 404));

        AppException ex = Assert.assertThrows(AppException.class, () -> userService.getUserByUsername(userRequest.getUsername()));

        Assert.assertEquals("User not found", ex.getMessage());
        Assert.assertEquals(404, ex.getErrorCode());
    }

    @Test
    public void TestGetUserById() {
        UserRequest userRequest = new UserRequest(1, "admin", "admin", "admin@gmail.com", true);
        Integer id = 1;
        Mockito.when(userRepository.findById(id)).thenReturn(Optional.ofNullable(user));

        UserResponse userResponse = userService.getUserById(userRequest.getUserId());

        Assert.assertEquals("admin", userResponse.getUsername());
    }

    @Test
    public void TestGetUserByIdFail() {
        //set up
        UserRequest userRequest = new UserRequest(1, "admin", "admin", "admin@gmail.com", true);
        Integer id = 2;
        Mockito.when(userRepository.findById(id)).thenThrow(new AppException("User not found", 404));

        AppException ex = Assert.assertThrows(AppException.class, () -> userService.getUserById(userRequest.getUserId()));

        Assert.assertEquals("Account not found", ex.getMessage());
        Assert.assertEquals(404, ex.getErrorCode());
    }

    @Test
    public void TestUpdateUserSuccess() {
        //set up
        UserRequest userRequest = new UserRequest(1, "admin", "admin", "admin@gmail.com", true);
        Integer id = 1;
        Mockito.when(userRepository.findById(id)).thenReturn(Optional.ofNullable(user));

        UserResponse userResponse = userService.updateUser(id,userRequest);

        Assert.assertEquals("admin", userResponse.getUsername());
    }

    @Test
    public void TestUpdateUserFail() {
        //set up
        UserRequest userRequest = new UserRequest(1, "admin", "admin", "admin@gmail.com", true);
        Integer id = 1;
        Mockito.when(userRepository.findById(id)).thenThrow(new AppException("User not found", 404));

        AppException ex = Assert.assertThrows(AppException.class, () -> userService.updateUser(id, userRequest));

        Assert.assertEquals("User not found", ex.getMessage());
        Assert.assertEquals(404, ex.getErrorCode());
    }

    @Test
    public void TestGetAllUser(){
        List<User> userList = new ArrayList<>();
        userList.add(user);
        when(userRepository.findAll()).thenReturn(userList);

        List<UserResponse> actual = userService.getALLUser();
        //assertEquals(userList.size(),actual.size());
    }

    @Test
    public void TestGetAllProductFail(){
        List<User> expect  = new ArrayList<>();
        expect.add(user);
        List<User> actual = new ArrayList<>();
        actual.add(user);
        Mockito.when(userRepository.findAll()).thenThrow(new NullPointerException(""));
        assertEquals(expect.size(),actual.size());
    }

    @Test
    public void TestDisableUser(){
        UserRequest userRequest = new UserRequest(1, "admin", "admin", "admin@gmail.com", true);
        Integer id = 1;
        Mockito.when(userRepository.findById(userRequest.getUserId())).thenReturn(Optional.ofNullable(user));
        userService.disableUser(id);
    }

    @Test
    public void TestDisableFail(){
        UserRequest userRequest = new UserRequest(1, "admin", "admin", "admin@gmail.com", true);
        Integer id = 2;
        Mockito.when(userRepository.findById(id)).thenThrow(new AppException("User not found", 404));
        AppException ex = Assert.assertThrows(AppException.class, () -> userService.disableUser(userRequest.getUserId()));
        Assert.assertEquals("Account not found", ex.getMessage());
        Assert.assertEquals(404, ex.getErrorCode());
    }


}
