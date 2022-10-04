package com.capstone.project.service.impl;
import com.capstone.project.domain.Role;
import com.capstone.project.domain.User;
import com.capstone.project.domain.UserStatus;
import com.capstone.project.dto.UserDTO;
import com.capstone.project.dto.UserRequest;
import com.capstone.project.mapper.UserMapper;
import com.capstone.project.repository.RoleRepository;
import com.capstone.project.repository.StatusRepository;
import com.capstone.project.repository.UserRepository;
import com.capstone.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final StatusRepository statusRepository;

    @Override
    public List<UserDTO> getALL() {
        return userRepository.findAll().stream().map(UserMapper :: convertToUserDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO create(UserRequest userRequest) {
        Optional<Role> role = roleRepository.findById(userRequest.getRoleId());
        Optional<UserStatus> userStatus = statusRepository.findById(userRequest.getStatusId());
        User user = userRepository.save(User.builder()
                        .username(userRequest.getUsername())
                        .password(userRequest.getPassword())
                        .email(userRequest.getEmail())
                        .role(role.get())
                        .status(userStatus.get())
                .build());
        return UserMapper.convertToUserDTO(user);
    }

    @Override
    public UserDTO delete(int id) {
        return null;
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
        user.setUserId(userDTO.getUserId());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setRole(userDTO.getRole());
        user.setStatus(userDTO.getStatus());

        return user;
    }
}
