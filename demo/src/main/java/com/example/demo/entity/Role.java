package com.example.demo.entity;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Role implements GrantedAuthority {
    @Id
    private String name;

    // Implement methods from GrantedAuthority interface
    @Override
    public String getAuthority() {
        return name;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
