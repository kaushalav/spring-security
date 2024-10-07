package com.kaushal.av.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kaushal.av.demo.model.Users;

public interface UserRepo extends JpaRepository<Users, Integer> {
    Users findByUsername(String username);
}
