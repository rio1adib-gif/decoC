package com.digitalevidence.evidencecustody.controller;

// REMOVED: import com.digitalevidence.evidencecustody.dto.UserRegisterDTO;
import com.digitalevidence.evidencecustody.entity.User;
import com.digitalevidence.evidencecustody.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Admin Controller for user management and role assignment.
 * Grounded in uploaded project context.
 */
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AuthService authService;

    public AdminController(AuthService authService) { 
        this.authService = authService; 
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() { 
        return ResponseEntity.ok(authService.getAllUsers()); 
    }

    @PutMapping("/users/{userId}/status")
    public ResponseEntity<String> updateUserStatus(@PathVariable Long userId, @RequestParam boolean enabled) {
        authService.updateUserStatus(userId, enabled);
        return ResponseEntity.ok(enabled ? "Enabled" : "Disabled");
    }

    @PutMapping("/users/{userId}/role")
    public ResponseEntity<String> updateUserRole(@PathVariable Long userId, @RequestParam String newRole) {
        authService.updateUserRole(userId, newRole);
        return ResponseEntity.ok("Role updated");
    }
}