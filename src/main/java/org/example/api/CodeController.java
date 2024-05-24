package org.example.api;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.CodeRequest;
import org.example.service.CodeService;
import org.example.service.UserService;
import org.example.utils.SessionUtils;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("api/codes/")
public class CodeController {

    private CodeService codeService;
    private UserService userService;
    private SessionUtils sessionUtils;

    public CodeController(CodeService codeService, UserService userService, SessionUtils sessionUtils) {
        this.codeService = codeService;
        this.userService = userService;
        this.sessionUtils = sessionUtils;
    }

    @PostMapping()
    public boolean unlockCode(@RequestBody CodeRequest codeRequest, HttpServletRequest request) {
        System.out.println(codeRequest);
        String newRole = codeService.unlockCode(codeRequest);
        if (newRole == null) {
            return false;
        } else {
            try {
                sessionUtils.setEncryptedSessionAttribute(request, "role", newRole);
                String username = sessionUtils.getDecryptedSessionAttribute(request, "username");
                userService.updateUsersRole(username, newRole);
                return true;
            } catch (RuntimeException re) {
                log.error(re.getMessage());
            } catch (Exception e) {
                log.error(e.getMessage());
            }
            return false;
        }
    }

}
