package com.splitwise.SplitwiseApp.strategies.settleUp;

import com.splitwise.SplitwiseApp.models.Expense;
import com.splitwise.SplitwiseApp.models.Transaction;

import java.util.List;

public interface SettleUpStrategy {
    List<Transaction> settleUp(List<Expense> expenses);
}
