package com.kaushal.av.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kaushal.av.demo.model.Users;
import com.kaushal.av.demo.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class UserController {
    
    @Autowired
    private UserService userService;
       
    @GetMapping("/user")
    public List<Users> getUsers() {
        List<Users> result = userService.getUsers();
        return result;
    }

    @GetMapping("/user/{id}")
    public Users getUser(@PathVariable Integer id) {
        return userService.getUser(id);
    }

    @PostMapping("/register")
    public Users createUser(@RequestBody Users user) {
        return userService.createUser(user);
    }

    @PutMapping("user/{id}")
    public Users updateUser(@PathVariable Integer id, @RequestBody Users user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("user/{id}")
    public String deleteUser(@PathVariable Integer id) {
        return userService.deleteUser(id);
    }

    @GetMapping("/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest httpServletRequest) {
        return (CsrfToken) httpServletRequest.getAttribute("_csrf");
   }
}


