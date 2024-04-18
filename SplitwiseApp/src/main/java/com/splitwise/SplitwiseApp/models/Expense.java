package com.splitwise.SplitwiseApp.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Expense {
    private String expenseId;
    private ExpenseType expenseType;
    private int amount;
    private User paidBy;
    private List<OwedBy> owedBy = new ArrayList<>();
}
