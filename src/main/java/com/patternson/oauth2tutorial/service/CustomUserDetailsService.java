package com.patternson.oauth2tutorial.service;

import com.patternson.oauth2tutorial.model.CustomUserDetails;
import com.patternson.oauth2tutorial.model.Users;
import com.patternson.oauth2tutorial.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> usersOptional = usersRepository.findByEmail(username);

        usersOptional
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));

                return usersOptional.map(CustomUserDetails::new)
                        .get();
    }
}
