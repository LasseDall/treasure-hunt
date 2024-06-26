package org.example.api;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.UserRequest;
import org.example.dto.UserResponse;
import org.example.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/users/")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String hej(HttpServletRequest httpServletRequest) {
        System.out.println(httpServletRequest.getSession().getAttribute("username"));
        return "hej";
    }

    @GetMapping("auth/users")
    public List<UserResponse> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("notes")
    public String getNotes(HttpServletRequest request) {
        return userService.getNotes(request);
    }

    @PostMapping("new-user")
    public UserResponse addUser(@RequestBody UserRequest userRequest) {
        return userService.addUser(userRequest);
    }

    @PutMapping("notes")
    public String updateNotes(@RequestBody String notes, HttpServletRequest request) {
        return userService.updateNotes(notes, request);
    }
}
