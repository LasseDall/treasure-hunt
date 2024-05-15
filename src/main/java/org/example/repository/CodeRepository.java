package org.example.repository;

import org.example.model.Code;
import org.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeRepository extends JpaRepository<Code, Integer> {
    public Code findByName(String name);
}
