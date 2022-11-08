package com.capstone.project.service.impl;

import com.capstone.project.config.exception.AppException;
import com.capstone.project.domain.User;
import com.capstone.project.repository.UserRepository;
import com.capstone.project.request.UserRequest;
import com.capstone.project.response.UserResponse;
import com.capstone.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Boolean checkEmailExist(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()){
            return true;
        }
        return false;
    }

    @Override
    public Boolean checkUserNameExist(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isPresent()){
            return true;
        }
        return false;
    }

    @Override
    public UserResponse getUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException("User not found", 404));
        return mapper.map(user, UserResponse.class);
    }

    @Override
    public UserResponse getUserById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException("Account not found", 404));
        return mapper.map(user, UserResponse.class);
    }

    @Override
    public List<UserResponse> getALL() {
        return userRepository.findAll()
                .stream()
                .map(user -> mapper.map(user, UserResponse.class))
                .collect(Collectors.toList());
    }


    @Override
    public UserResponse disableUser(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException("Account not found", 404));
        user.setStatus(false);
        userRepository.save(user);
        return mapper.map(user, UserResponse.class);
    }

    @Override
    public UserResponse create(UserRequest request) {
        if(!checkUserNameExist(request.getUsername()) & !checkEmailExist(request.getEmail())) {
            User user = userRepository.save(User.builder()
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .email(request.getEmail())
                    .status(true)
                    .build());
            userRepository.setRole(user.getUserId(), 2);
            return mapper.map(user, UserResponse.class);
        }
        return null;
    }
    public String encodePwd(String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(password);
        String encoded = encoder.encode(password);
        return encoded;
    }

    @Override
    public UserResponse update(Integer id, UserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException("Account not found", 404));

        user.setUsername(request.getUsername());
        user.setPassword(encodePwd(request.getPassword()));
        user.setEmail(request.getEmail());
        userRepository.save(user);
        return mapper.map(user, UserResponse.class);
    }

    @Override
    public UserResponse changePwd(String username, UserRequest userRequest) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException("Account not found", 404));
        user.setPassword(encodePwd(userRequest.getPassword()));
        userRepository.save(user);
        return mapper.map(user, UserResponse.class);
    }

    @Override
    public UserResponse ForgotPwd(String username) {
        return null;
    }


}
