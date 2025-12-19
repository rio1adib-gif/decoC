package com.digitalevidence.evidencecustody.service;

import com.digitalevidence.evidencecustody.dto.UserRegisterDTO;
import com.digitalevidence.evidencecustody.entity.User;
import com.digitalevidence.evidencecustody.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // =================================================
    // CREATE USER (ADMIN ONLY)
    // =================================================
    public User registerUser(UserRegisterDTO dto) {

        if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole("ROLE_" + dto.getRole().toUpperCase()); // ROLE_ADMIN / ROLE_OFFICER / ROLE_FORENSIC
        user.setEnabled(true);

        return userRepository.save(user);
    }

    // =================================================
    // VIEW ALL USERS (ADMIN)
    // =================================================
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // =================================================
    // ENABLE / DISABLE USER (ADMIN)
    // =================================================
    public void updateUserStatus(Long userId, boolean enabled) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setEnabled(enabled);
        userRepository.save(user);
    }
}
