package com.splitwise.SplitwiseApp.commands;

import com.splitwise.SplitwiseApp.controllers.ExpenseController;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
public class ShowAllUsersBalanceCommand implements ICommand {

    private ExpenseController expenseController;
    private String command;

    public ShowAllUsersBalanceCommand(String command, ExpenseController controller) {
        this.command = command;
        this.expenseController = controller;
    }

    @Override
    public void execute() {
        System.out.println(this.expenseController.getAllExpenses());
    }
}
