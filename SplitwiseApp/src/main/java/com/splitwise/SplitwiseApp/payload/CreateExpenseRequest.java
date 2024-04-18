package com.splitwise.SplitwiseApp.payload;

import com.splitwise.SplitwiseApp.models.ExpenseType;
import com.splitwise.SplitwiseApp.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
public class CreateExpenseRequest {
    String paidBy;
    int amount;
    int totalUsers;
    ExpenseType expenseType;
    List<String> users;
    Optional<List<Integer>> percentageList;
    Optional<List<Integer>> exactAmounts;
}
