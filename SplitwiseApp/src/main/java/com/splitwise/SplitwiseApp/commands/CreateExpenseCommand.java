package com.splitwise.SplitwiseApp.commands;

import com.splitwise.SplitwiseApp.controllers.ExpenseController;
import com.splitwise.SplitwiseApp.models.ExpenseType;
import com.splitwise.SplitwiseApp.models.User;
import com.splitwise.SplitwiseApp.payload.CreateExpenseRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Getter
@Setter
public class CreateExpenseCommand implements ICommand {

    public ExpenseController expenseController;

    private String command;

    public CreateExpenseCommand() {

    }

    public CreateExpenseCommand(String command, ExpenseController controller) {
        this.command = command;
        this.expenseController = controller;
    }

    @Override
    public void execute() {
        String[] input = command.split(" ");
//        String paidBy;
//        int amount;
//        int totalUsers;
//        ExpenseType expenseType;
//        List<String> users;
//        Optional<List<Integer>> percentageList;
//        Optional<List<Integer>> exactAmounts;
//        EXPENSE u1 1000 4 u1 u2 u3 u4 EQUAL
        CreateExpenseRequest request = new CreateExpenseRequest();
        request.setPaidBy(input[1]);
        request.setAmount(Integer.parseInt(input[2]));
        int userCount = Integer.parseInt(input[3]);
        List<String> users = new ArrayList<>();
        int idx = 4;
        while(userCount-->0) {
            users.add(input[idx++]);
        }
        request.setUsers(users);
        String expenseType = input[idx++];
        request.setExpenseType(ExpenseType.valueOf(expenseType));
        List<Integer> percentagesList = new ArrayList<>();
        List<Integer> exactAmounts = new ArrayList<>();
        int count = Integer.parseInt(input[3]);
        request.setTotalUsers(count);
        if(expenseType.equals("PERCENT")) {
            while(count-->0)
                percentagesList.add(Integer.parseInt(input[idx++]));
            request.setPercentageList(Optional.of(percentagesList));
        } else if(expenseType.equals("EXACT")) {
            while(count-->0) {
                exactAmounts.add(Integer.parseInt(input[idx++]));
            }
            request.setExactAmounts(Optional.of(exactAmounts));
        }
        System.out.println(expenseController.createExpense(request));
    }
}
