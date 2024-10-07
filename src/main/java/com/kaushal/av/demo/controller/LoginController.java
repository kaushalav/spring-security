package com.kaushal.av.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kaushal.av.demo.model.Users;
import com.kaushal.av.demo.service.UserService;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;
    
    @PostMapping("/login")
    public String login(@RequestBody Users user) {
        System.out.println(user);
        return userService.verify(user);
    }
}
