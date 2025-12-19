package com.digitalevidence.evidencecustody.dto;

public class UserRegisterDTO {

    private String username;
    private String password;
    private String role; // ROLE_ADMIN / ROLE_OFFICER / ROLE_FORENSIC

    // ===== Getters & Setters =====
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
}
