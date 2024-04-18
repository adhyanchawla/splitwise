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
public class UserRepo {
    private Map<String, User> userMap = new HashMap<>();
    private Map<String, List<Expense>> userExpenseMap = new HashMap<>();
}
