package com.capstone.project.service.impl;

import com.capstone.project.config.exception.AppException;
import com.capstone.project.domain.User;
import com.capstone.project.repository.RoleRepository;
import com.capstone.project.repository.StatusRepository;
import com.capstone.project.repository.UserRepository;
import com.capstone.project.request.UserRequest;
import com.capstone.project.response.UserResponse;
import com.capstone.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @Override
    public UserResponse getUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException("User not found", 404));
        return mapper.map(user, UserResponse.class);
    }

    @Override
    public UserResponse getUserById(String username) {
        return null;
    }

    @Override
    public List<UserResponse> getALL() {
        return userRepository.findAll()
                .stream()
                .map(user -> mapper.map(user, UserResponse.class))
                .collect(Collectors.toList());
    }


    public List<UserResponse> findAll() {
        return userRepository.findAll()
                .stream()
                .map(account -> mapper.map(account, UserResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse disableUser(String username) {
        return null;
    }

    @Override
    public UserResponse create(UserRequest request) {
        return null;
    }

    @Override
    public UserResponse update(String username, UserRequest request) {
        return null;
    }

    @Override
    public UserResponse changePwd(long id, String newPwd) {
        return null;
    }

    @Override
    public UserResponse delete(int id, String newPwd) {
        return null;
    }

}
