package org.example.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.UserRequest;
import org.example.dto.UserResponse;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.example.utils.SessionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@Service
public class UserService {

    private BCryptPasswordEncoder passwordEncoder;

    private UserRepository userRepository;

    private SessionUtils sessionUtils;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, SessionUtils sessionUtils) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.sessionUtils = sessionUtils;
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
                    .phone(userRequest.getPhone())
                    .email(userRequest.getEmail())
                    .name(userRequest.getName())
                    .role("new")
                    .notes("Collect your notes here..")
                    .build();
            userRepository.save(newUser);
            return new UserResponse(newUser);
        }
        return null;
    }

    public void updateUsersRole(String username, String newRole) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            user.setRole(newRole);
            userRepository.save(user);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    public String updateNotes(String notes, HttpServletRequest request) {
        String username = null;
        try {
            username = sessionUtils.getDecryptedSessionAttribute(request, "username");
        } catch (RuntimeException re) {
            log.error(re.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        if (username != null) {
            User user = userRepository.findByUsername(username);

            if (user != null) {
                user.setNotes(notes);
                userRepository.save(user);
                return notes;
            }
        }
        return null;
    }

    public String getNotes(HttpServletRequest request) {
        String username = null;
        try {
            username = sessionUtils.getDecryptedSessionAttribute(request, "username");
        } catch (RuntimeException re) {
            log.error(re.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        if (username != null) {
            User user = userRepository.findByUsername(username);

            if (user != null) {
                return user.getNotes();
            }
        }
        return null;
    }
}
