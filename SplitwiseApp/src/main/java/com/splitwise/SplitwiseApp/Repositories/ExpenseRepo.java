package com.splitwise.SplitwiseApp.Repositories;

import com.splitwise.SplitwiseApp.models.Expense;
import com.splitwise.SplitwiseApp.models.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Component
public class ExpenseRepo {
    Map<String, Expense> expenseMap = new HashMap<>();
    Map<String, Map<String, Integer>> userExpenseMap = new HashMap<>();
    //Map<String, List<User>> expenseUserMap = new HashMap<>();
}
