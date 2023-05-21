package com.example.springbootb2.security;

import com.example.springbootb2.exception.UserNotExistsException;
import com.example.springbootb2.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import com.example.springbootb2.model.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByPhone(username)
                .orElseThrow(() -> new UsernameNotFoundException("There is no user with such username"));

        return new org.springframework.security.core.userdetails
                .User(user.getPhone(),
                user.getPassword(),
                user.isActive(),
                user.isActive(),
                user.isActive(),
                user.isActive(),
                List.of(new SimpleGrantedAuthority(user.getRole())));
    }
}
