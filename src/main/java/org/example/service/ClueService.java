package org.example.service;

import org.example.dto.ClueResponse;
import org.example.model.Clue;
import org.example.repository.ClueRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClueService {

    private ClueRepository clueRepository;

    public ClueService(ClueRepository clueRepository) {
        this.clueRepository = clueRepository;
    }

    public List<ClueResponse> getClues(String role) {
        if (role != null) {
            List<Clue> clues = clueRepository.findAll();
            List<Clue> presentClues = clues.stream().filter(clue -> clue.getRole().length() <= role.length()).toList();
            return presentClues.stream().map(ClueResponse::new).toList();
        } else {
            return null;
        }
    }
}
