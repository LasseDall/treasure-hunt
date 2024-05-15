package org.example.api;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.CodeRequest;
import org.example.service.CodeService;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/codes/")
public class CodeController {

    private CodeService codeService;

    public CodeController(CodeService codeService) {
        this.codeService = codeService;
    }

    @PostMapping()
    public boolean unlockCode(@RequestBody CodeRequest codeRequest, HttpServletRequest httpServletRequest) {
        String newRole = codeService.unlockCode(codeRequest);
        if (newRole == null) {
            return false;
        } else {
            HttpSession session = httpServletRequest.getSession(false);
            session.setAttribute("role", newRole);
            return true;
        }
    }

}
