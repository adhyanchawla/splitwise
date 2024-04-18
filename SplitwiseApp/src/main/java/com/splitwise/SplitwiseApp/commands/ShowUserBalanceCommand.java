package com.splitwise.SplitwiseApp.commands;

import com.splitwise.SplitwiseApp.controllers.ExpenseController;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class ShowUserBalanceCommand implements ICommand {
    private ExpenseController expenseController;
    String command;
    public ShowUserBalanceCommand(String command, ExpenseController controller) {
        this.command = command;
        this.expenseController = controller;
    }
    @Override
    public void execute() {
        String userId = command.split(" ")[1];
        System.out.println(expenseController.getExpensesByUser(userId));
    }
}
