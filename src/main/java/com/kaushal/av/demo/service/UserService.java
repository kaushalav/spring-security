package com.kaushal.av.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.kaushal.av.demo.model.Users;
import com.kaushal.av.demo.repo.UserRepo;

@Service
public class UserService {
    
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JWTService jwtService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public List<Users> getUsers() {
        return userRepo.findAll();
    }

    public Users getUser(Integer id) {
        return userRepo.findById(id).orElse(new Users(0, "abc", "efg"));
    }
    
    public Users createUser(Users user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userRepo.save(user);
        return user;
    }

    public Users updateUser(Integer id, Users user) {
        Users user1 = userRepo.findById(id).orElse(new Users(0, "abc", "def"));
        user1.setPassword(user.getPassword());
        user1.setUsername(user.getUsername());
        return user1;
    }
    
    public String deleteUser(Integer id) {
        userRepo.deleteById(id);
        return "User is deleted";
    }

    public String verify(Users user) {
        Authentication authentication = 
            authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                                user.getUsername(), user.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getUsername());
        }
        return "fail";   
    }

}
