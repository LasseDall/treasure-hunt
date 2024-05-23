package org.example.api;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.CodeRequest;
import org.example.service.CodeService;
import org.example.service.UserService;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("api/codes/")
public class CodeController {

    private CodeService codeService;

    private UserService userService;

    public CodeController(CodeService codeService, UserService userService) {
        this.codeService = codeService;
        this.userService = userService;
    }

    @PostMapping()
    public boolean unlockCode(@RequestBody CodeRequest codeRequest, HttpServletRequest httpServletRequest) {
        System.out.println(codeRequest);
        String newRole = codeService.unlockCode(codeRequest);
        if (newRole == null) {
            return false;
        } else {
            HttpSession session = httpServletRequest.getSession(false);
            session.setAttribute("role", newRole);
            String username = (String) session.getAttribute("username");
            userService.updateUsersRole(username, newRole);
            return true;
        }
    }

}
