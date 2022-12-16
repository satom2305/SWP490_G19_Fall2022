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

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class JwtAuthenticationControllerTest {
    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

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
}
