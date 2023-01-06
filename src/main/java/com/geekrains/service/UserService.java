package com.geekrains.service;

import com.geekrains.Logging.LogExecutionTime;
import com.geekrains.model.Role;
import com.geekrains.model.User;
import com.geekrains.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @LogExecutionTime
    public Optional<User> findByUsername(String username){
        return userRepository.findById(username);
    }

    @LogExecutionTime
    public User findAuthenticatedUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> maybeUser = userRepository.findById(auth.getName());
        if(maybeUser.isPresent()){
            return maybeUser.get();
        }else{
            return null;
        }
    }

    @LogExecutionTime
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @LogExecutionTime
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(()->new UsernameNotFoundException(String.format("User '%s' not found",username)));
        System.out.println(user.getAuthorities());
        return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(),user.getAuthorities());
    }

    @LogExecutionTime
    public String returnHighestRole(String username){
        Set<Role> roles =  userRepository.findById(username).get().getRoles();
        if(roles.contains(Role.ADMIN)){
            return "admin";
        }else{
            return "user";
        }
    }


}
