package com.splitwise.SplitwiseApp.Services;

import com.splitwise.SplitwiseApp.models.Expense;
import com.splitwise.SplitwiseApp.models.Transaction;
import com.splitwise.SplitwiseApp.payload.CreateExpenseRequest;

import java.util.List;

public interface ExpenseService {
    Expense createExpense(CreateExpenseRequest request);

    List<String> getExpensesByUser(String userId);

    List<String> getAllExpenses();
    List<Transaction> settleUpUser(String userId);
}
