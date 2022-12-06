package com.capstone.project.service.impl;

import com.capstone.project.config.exception.AppException;
import com.capstone.project.domain.User;
import com.capstone.project.repository.UserRepository;
import com.capstone.project.request.UserRequest;
import com.capstone.project.response.UserResponse;
import com.capstone.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        if (user.isPresent()) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean checkUserNameExist(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
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
    public List<UserResponse> getALLUser() {
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
    public Integer createUser(UserRequest request) {
            if(checkEmailExist(request.getEmail())){
                return 1;
            } else if(checkUserNameExist(request.getUsername())){
                return 2;
            } else {
                User user = userRepository.save(User.builder()
                        .username(request.getUsername())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .email(request.getEmail())
                        .status(true)
                        .build());
                userRepository.setRole(user.getUserId(), 2);
                return 0;
            }
    }

    public String encodePwd(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(password);
        String encoded = encoder.encode(password);
        return encoded;
    }

    @Override
    public UserResponse updateUser(Integer id, UserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException("Account not found", 404));

        user.setUsername(request.getUsername());
        user.setPassword(encodePwd(request.getPassword()));
        user.setEmail(request.getEmail());
        userRepository.save(user);
        return mapper.map(user, UserResponse.class);
    }

    @Override
    public int changePwd(String OldPassword, String NewPassword) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            User user = userRepository.findByUsername(authentication.getName())
                    .orElseThrow(() -> new AppException("Account not found", 404));
            boolean checkOld = passwordEncoder.matches(OldPassword, user.getPassword());
            boolean checkNew = passwordEncoder.matches(OldPassword, user.getPassword());
            if (!checkOld) {
                return 1;
            } else if (!checkNew) {
                return 2;
            } else {
                user.setPassword(encodePwd(NewPassword));
                userRepository.save(user);
            }
        }
        return 0;
    }

    @Override
    public UserResponse ForgotPwd(String username) {
        return null;
    }

    @Override
    public UserResponse createStaff(UserRequest request) {
        if (!checkUserNameExist(request.getUsername()) & !checkEmailExist(request.getEmail())) {
            User user = userRepository.save(User.builder()
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .email(request.getEmail())
                    .status(true)
                    .build());
            userRepository.setRole(user.getUserId(), 3);
            return mapper.map(user, UserResponse.class);
        }
        return null;
    }

    @Override
    public Boolean checkPwd(String username, UserRequest request) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException("Account not found", 404));
        boolean result = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if (result) {
            return true;
        }
        return false;
    }


}
