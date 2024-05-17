package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.model.Clue;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class ClueResponse {

    private int id;

    private String text;

    private byte[] image;

    public ClueResponse(Clue clue) {
        this.id = clue.getId();
        this.text = clue.getText();
        this.image = clue.getImage();
    }
}
