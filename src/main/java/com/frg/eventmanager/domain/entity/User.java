package com.frg.eventmanager.domain.entity;

import com.frg.eventmanager.domain.enums.UserRole;

import java.time.LocalDateTime;
import java.util.UUID;

public class User {

    private UUID id;
    private String name;
    private String email;
    private String password;
    private UserRole role;
    private LocalDateTime createdAt;

    public User(UUID id, String name, String email, String password, UserRole role, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public UserRole getRole() {
        return role;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void updatePassword(String password) {
        this.password = password;
    }
}
