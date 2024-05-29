package org.example.model;

import jakarta.persistence.*;
import lombok.*;
import org.example.dto.UserRequest;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(schema = "treasure_hunt_schema", name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;

    private String password;

    private String email;

    private String phone;

    private String name;

    private String role;

    private String notes;
}
