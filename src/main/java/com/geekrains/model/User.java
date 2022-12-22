package com.geekrains.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
public class User/* implements UserDetails */ {
    @Id
    @Column
    private String userName;
    @Column
    private String password;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role",joinColumns = @JoinColumn(name = "user_name"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @OneToMany(mappedBy = "user")
    private Set<CartItem> cartItems;

    //for UserDetails
/*
    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isEnabled = true;

 */



    public User() {
    }

    public User(String userName, String password, Set<Role> roles, Set<CartItem> cartItems) {
        this.userName = userName;
        this.password = password;
        this.roles = roles;
        this.cartItems = cartItems;
    }

    //for UserDteials:
/*
    @Override
    public boolean isAccountNonExpired() {
        return true;
      //  return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
      //  return isAccountNonLocked;
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
       // return isCredentialsNonExpired;
        return true;
    }

    @Override
    public boolean isEnabled() {
      //  return isEnabled;
        return true;
    }

 */

    public List<? extends GrantedAuthority> getAuthorities(){
         return roles.stream().map(role -> new SimpleGrantedAuthority(role.name())).collect(Collectors.toList());
    }

    public String getUsername(){
        return userName;
    }

    //

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public boolean hasRole(String roleName) {
        Iterator<Role> iterator = this.roles.iterator();
        while (iterator.hasNext()) {
            Role role = iterator.next();
            if (role.name().equals(roleName)) {
                return true;
            }
        }

        return false;
    }
}

