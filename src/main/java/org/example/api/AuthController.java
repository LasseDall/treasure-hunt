package org.example.api;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.LoginRequest;
import org.example.service.AuthService;
import org.example.utils.SessionUtils;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/auth/")
public class AuthController {

    private AuthService authService;
    private SessionUtils sessionUtils;

    public AuthController(AuthService authService, SessionUtils sessionUtils) {
        this.authService = authService;
        this.sessionUtils = sessionUtils;
    }

    @PostMapping(path = "login")
    public boolean login(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        String role = authService.login(loginRequest.getUsername(), loginRequest.getPassword());
        if (role != null) {
            try {
                sessionUtils.setEncryptedSessionAttribute(request, "username", loginRequest.getUsername());
                sessionUtils.setEncryptedSessionAttribute(request, "role", role);
                return true;
            } catch (RuntimeException re) {
                log.error(re.getMessage());
            } catch (Exception e) {
                log.error(e.getMessage());
            }
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
