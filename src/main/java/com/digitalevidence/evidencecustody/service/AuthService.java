package com.digitalevidence.evidencecustody.service;

import com.digitalevidence.evidencecustody.dto.UserRegisterDTO; // Required for registration
import com.digitalevidence.evidencecustody.entity.User;
import com.digitalevidence.evidencecustody.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder; // Required for password hashing
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository repo, PasswordEncoder passwordEncoder) {
        this.userRepository = repo;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Registers a new user with full professional identification.
     * Maps name, badge number, and department from the DTO to the Entity.
     */
    public User registerUser(UserRegisterDTO dto) {
        // Validation: Check if username already exists
        if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        User user = new User();
        user.setFullName(dto.getFullName());
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        
        // Ensure role has the correct prefix
        String role = dto.getRole().toUpperCase();
        if (!role.startsWith("ROLE_")) {
            role = "ROLE_" + role;
        }
        user.setRole(role);

        // Map the professional identification fields
        user.setBadgeNumber(dto.getBadgeNumber());
        user.setDepartment(dto.getDepartment());
        user.setEnabled(true);

        return userRepository.save(user);
    }

    /**
     * View all users (Admin Dashboard)
     */
    public List<User> getAllUsers() { 
        return userRepository.findAll(); 
    }

    /**
     * Enable or Disable a user account (Admin Dashboard)
     */
    public void updateUserStatus(Long userId, boolean enabled) {
        User user = userRepository.findById(userId).orElseThrow();
        user.setEnabled(enabled);
        userRepository.save(user);
    }

    /**
     * Update a user's role (Admin Dashboard)
     */
    public void updateUserRole(Long userId, String newRole) {
        User user = userRepository.findById(userId).orElseThrow();
        if (!newRole.startsWith("ROLE_")) {
            newRole = "ROLE_" + newRole.toUpperCase();
        }
        user.setRole(newRole);
        userRepository.save(user);
    }
}