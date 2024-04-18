package com.splitwise.SplitwiseApp.SplitFactory;

import com.splitwise.SplitwiseApp.Repositories.UserRepo;
import com.splitwise.SplitwiseApp.models.Expense;
import com.splitwise.SplitwiseApp.models.ExpenseType;
import com.splitwise.SplitwiseApp.payload.CreateExpenseRequest;
import com.splitwise.SplitwiseApp.strategies.EqualSplitStrategy;
import com.splitwise.SplitwiseApp.strategies.ExactSplitStrategy;
import com.splitwise.SplitwiseApp.strategies.PercentSplitStrategy;
import com.splitwise.SplitwiseApp.strategies.SplitStrategy;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class SplitFactory {

    public static SplitStrategy getSplitStrategy(CreateExpenseRequest request, Expense expense, UserRepo userRepo) {
        if(request.getExpenseType().equals(ExpenseType.EQUAL)) {
            return new EqualSplitStrategy(request, expense, userRepo);
        } else if(request.getExpenseType().equals(ExpenseType.EXACT)) {
            return new ExactSplitStrategy(request, expense, userRepo);
        } else return new PercentSplitStrategy(request, expense, userRepo);
    }
}
