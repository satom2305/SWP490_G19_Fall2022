package com.capstone.project.service.impl;

import com.capstone.project.domain.User;
import com.capstone.project.dto.UserDTO;
import com.capstone.project.mapper.UserMapper;
import com.capstone.project.repository.UserRepository;
import com.capstone.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<UserDTO> getALL() {
        return userRepository.findAll().stream().map(UserMapper::convertToUserDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO create(UserDTO userDTO) {
        return UserMapper.convertToUserDTO(userRepository.save(convertToUser(userDTO)));
    }

    @Override
    public UserDTO getUserById(long id) {
        return null;
    }

    @Override
    public UserDTO update(UserDTO userDTO) {
        return null;
    }

    @Override
    public User getUserByUsername(String username) {
        return null;
    }

    @Override
    public UserDTO changePwd(long id, String newPwd) {
        return null;
    }

    public User convertToUser(UserDTO userDTO){
        User user = new User();
        user.setUserID(userDTO.getUserID());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setRoleID(userDTO.getRoleID());
        user.setStatusID(userDTO.getStatusID());

        return user;
    }
}
