package com.example.security.service;

import com.example.security.entity.User;
import com.example.security.exception.UserNotValid;
import com.example.security.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userObje =  userRepository.findByUserName(username);

        if(Objects.isNull(userObje)){
            System.out.println("Your not exits");
            throw  new UsernameNotFoundException("User not found");
        }
        return new CustomeUserDetails(userObje);
    }
}
