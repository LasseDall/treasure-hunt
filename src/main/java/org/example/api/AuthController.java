package org.example.api;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.LoginRequest;
import org.example.service.AuthService;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/auth/")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(path = "login")
    public boolean login(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        String isAuthenticated = authService.login(loginRequest.getUsername(), loginRequest.getPassword());
        if (isAuthenticated != null) {
            HttpSession session = request.getSession();
            session.setAttribute("username", loginRequest.getUsername());
            session.setAttribute("role", isAuthenticated);
            return true;
        }
        return false;
    }

    @PostMapping(path = "logout")
    public boolean logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
            return true;
        }
        return false;
    }
}
