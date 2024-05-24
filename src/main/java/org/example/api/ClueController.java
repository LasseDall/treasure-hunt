package org.example.api;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.ClueResponse;
import org.example.service.ClueService;
import org.example.utils.SessionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/api/clues/")
public class ClueController {

    private ClueService clueService;
    private SessionUtils sessionUtils;

    public ClueController(ClueService clueService, SessionUtils sessionUtils) {
        this.clueService = clueService;
        this.sessionUtils = sessionUtils;
    }

    @GetMapping
    public List<ClueResponse> getClues(HttpServletRequest request) {
        try {
            List<ClueResponse> clues = clueService.getClues((String) sessionUtils.getDecryptedSessionAttribute(request, "role"));
            return clues;
        } catch (RuntimeException re) {
            log.error(re.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
