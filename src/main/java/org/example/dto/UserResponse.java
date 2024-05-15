package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.model.User;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class UserResponse {

    private String username;

    public UserResponse (User user) {
        this.username = user.getUsername();
    }
}
