package com.splitwise.SplitwiseApp.commands;

import com.splitwise.SplitwiseApp.controllers.UserController;
import com.splitwise.SplitwiseApp.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateUserCommand implements ICommand {

    private UserController userController;
    String command;

    public CreateUserCommand() {

    }
    public CreateUserCommand(String command, UserController controller) {
        this.command = command;
        this.userController = controller;
    }

    @Override
    public void execute() {
        String[] input = command.split(" ");
        String id = input[1];
        String name = input[2];
        String email = input[3];
        String phone = input[4];
        User user = new User(id, name, email, phone);
        System.out.println(this.userController.createUser(user));
    }
}
