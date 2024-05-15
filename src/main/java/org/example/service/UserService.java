package org.example.service;

import org.example.dto.UserRequest;
import org.example.dto.UserResponse;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private BCryptPasswordEncoder passwordEncoder;

    private UserRepository userRepository;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserResponse> getUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserResponse::new).toList();
    }

    public UserResponse addUser(UserRequest userRequest) {
        List<User> users = userRepository.findAll();
        boolean userExists = users.stream().anyMatch(user -> user.getEmail().equals(userRequest.getEmail()) || user.getUsername().equals(userRequest.getEmail()));
        if (!userExists) {
            String encodedPassword = passwordEncoder.encode(userRequest.getPassword());

            User newUser = User.builder()
                    .username(userRequest.getUsername())
                    .password(encodedPassword)
                    .role("new")
                    .build();
            userRepository.save(newUser);
            return new UserResponse(newUser);
        }
        return null;
    }
}
