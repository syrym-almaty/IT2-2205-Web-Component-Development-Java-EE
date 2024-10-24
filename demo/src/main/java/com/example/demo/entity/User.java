package com.example.demo.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    private String username;
    private String password;
    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>(); // Инициализация множества ролей

    // Implement methods from UserDetails interface

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnabled() {
        this.enabled = true;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Вы можете добавить логику для контроля, если нужно
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Вы можете добавить логику для контроля, если нужно
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Вы можете добавить логику для контроля, если нужно
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
