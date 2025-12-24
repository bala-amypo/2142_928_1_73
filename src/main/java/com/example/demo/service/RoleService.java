package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Role;
import com.example.demo.repository.RoleRepository;

@Service
public class RoleService {

    private final RoleRepository repository;

    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    public Role createRole(Role role) {
        return repository.save(role);
    }

    public List<Role> getAllRoles() {
        return repository.findAll();
    }

    public Role getRoleByName(String name) {
        return repository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Role not found"));
    }
}
