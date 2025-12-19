package com.digitalevidence.evidencecustody.controller;

import com.digitalevidence.evidencecustody.dto.UserRegisterDTO;
import com.digitalevidence.evidencecustody.entity.User;
import com.digitalevidence.evidencecustody.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AuthService authService;

    public AdminController(AuthService authService) {
        this.authService = authService;
    }

    // CREATE USER (ADMIN ONLY)
    @PostMapping("/create-user")
    public ResponseEntity<User> createUser(@RequestBody UserRegisterDTO dto) {
        // Logic: Hashes password via BCrypt inside AuthService before saving
        User createdUser = authService.registerUser(dto);
        return ResponseEntity.ok(createdUser);
    }

    // VIEW ALL USERS (ADMIN ONLY)
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(authService.getAllUsers());
    }

    // ENABLE / DISABLE USER (ADMIN ONLY)
    // URL: http://localhost:9090/api/admin/users/1/status?enabled=false
    @PutMapping("/users/{userId}/status")
    public ResponseEntity<String> updateUserStatus(
            @PathVariable Long userId,
            @RequestParam boolean enabled) {

        authService.updateUserStatus(userId, enabled);
        return ResponseEntity.ok(
                enabled ? "User enabled successfully" : "User disabled successfully"
        );
    }
}