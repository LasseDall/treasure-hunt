package org.example.service;

import org.example.dto.CodeRequest;
import org.example.model.Code;
import org.example.repository.CodeRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CodeService {

    private CodeRepository codeRepository;

    private BCryptPasswordEncoder passwordEncoder;

    public CodeService(CodeRepository codeRepository, BCryptPasswordEncoder passwordEncoder) {
        this.codeRepository = codeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String unlockCode(CodeRequest codeRequest) {
        Code code = codeRepository.findByName(codeRequest.getName());

        if (!passwordEncoder.matches(codeRequest.getPassword(), code.getPassword())) {
            return null;
        } else {
            return code.getNewRole();
        }
    }
}
