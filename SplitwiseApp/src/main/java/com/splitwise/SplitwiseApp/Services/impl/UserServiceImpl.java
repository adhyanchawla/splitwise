package com.splitwise.SplitwiseApp.Services.impl;

import com.splitwise.SplitwiseApp.Repositories.UserRepo;
import com.splitwise.SplitwiseApp.Services.UserService;
import com.splitwise.SplitwiseApp.models.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@Getter
@Setter
public class UserServiceImpl implements UserService {

    UserRepo userRepo;

    public UserServiceImpl(UserRepo repo) {
        this.userRepo = repo;
    }

    @Override
    public User createUser(User user) {
        this.getUserRepo().getUserMap().put(user.getUserId(), user);
        return user;
    }
}
