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

    private String text;

    private byte[] image;

    public ClueResponse(Clue clue) {
        this.text = clue.getText();
        this.image = clue.getImage();
    }
}
