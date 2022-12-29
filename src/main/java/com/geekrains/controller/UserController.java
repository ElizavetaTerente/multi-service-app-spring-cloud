package com.geekrains.controller;

import com.geekrains.model.User;
import com.geekrains.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/admin/users")
    public List<User> allUserList(){
        return userService.findAll();
    }
}
