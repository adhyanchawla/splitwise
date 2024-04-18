package com.splitwise.SplitwiseApp.controllers;

import com.splitwise.SplitwiseApp.Services.UserService;
import com.splitwise.SplitwiseApp.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public User createUser(User user) {
        return this.userService.createUser(user);
    }
}
