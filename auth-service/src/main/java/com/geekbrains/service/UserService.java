package com.geekbrains.service;

import com.geekbrains.model.Role;
import com.geekbrains.model.User;
import com.geekbrains.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    public Collection<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> findByUsername(String username){
        return userRepository.findById(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(()->new UsernameNotFoundException(String.format("User '%s' not found",username)));
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),user.getAuthorities());
    }

    public boolean existByUsername(String username){
        return userRepository.existsByUserName(username);
    }

    public void addUser(String username, String password){
        Set<Role> roles = new HashSet<>();
        roles.add(Role.USER);
        userRepository.save(new User(username,password,roles));
    }



}
