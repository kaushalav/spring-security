package com.kaushal.av.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kaushal.av.demo.model.Users;
import com.kaushal.av.demo.model.UserPrincipal;
import com.kaushal.av.demo.repo.UserRepo;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepo.findByUsername(username);
        if (user == null) {
            System.out.println("User not found : "+ username);
            throw new UsernameNotFoundException("user not found");
        }
        return new UserPrincipal(user);
    }
    
}