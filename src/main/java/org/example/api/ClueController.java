package org.example.api;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.ClueResponse;
import org.example.model.Clue;
import org.example.service.ClueService;
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

    public ClueController(ClueService clueService) {
        this.clueService = clueService;
    }

    @GetMapping
    public List<ClueResponse> getClues(HttpServletRequest httpServletRequest) {
        return clueService.getClues((String) httpServletRequest.getSession().getAttribute("role"));
    }
}
