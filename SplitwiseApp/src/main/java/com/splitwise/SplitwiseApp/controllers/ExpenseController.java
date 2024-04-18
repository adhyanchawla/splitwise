package com.splitwise.SplitwiseApp.controllers;

import com.splitwise.SplitwiseApp.Services.ExpenseService;
import com.splitwise.SplitwiseApp.models.Expense;
import com.splitwise.SplitwiseApp.payload.CreateExpenseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ExpenseController {

    private ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    public Expense createExpense(CreateExpenseRequest request) {
        return this.expenseService.createExpense(request);
    }

    public List<String> getExpensesByUser(String userId) {
        return this.expenseService.getExpensesByUser(userId);
    }

    public List<String> getAllExpenses() {
        return this.expenseService.getAllExpenses();
    }
}
