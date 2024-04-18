package com.splitwise.SplitwiseApp.strategies;

import com.splitwise.SplitwiseApp.Repositories.ExpenseRepo;
import com.splitwise.SplitwiseApp.Repositories.UserRepo;
import com.splitwise.SplitwiseApp.models.Expense;
import com.splitwise.SplitwiseApp.models.OwedBy;
import com.splitwise.SplitwiseApp.models.User;
import com.splitwise.SplitwiseApp.payload.CreateExpenseRequest;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@NoArgsConstructor
public class EqualSplitStrategy implements SplitStrategy {

    private CreateExpenseRequest request;

    private Expense expense;


    private UserRepo userRepo;

    public EqualSplitStrategy(CreateExpenseRequest request, Expense expense, UserRepo userRepo) {
        this.request = request;
        this.expense = expense;
        this.userRepo = userRepo;
    }

    @Override
    public void calculateExpense() {
        int expenseAmount = request.getAmount();
        int noOfUsers = request.getTotalUsers();
        int amountDivided = expenseAmount / noOfUsers;
        String paidBy = request.getPaidBy();
        List<String> allUsers = request.getUsers();

        List<OwedBy> owed = expense.getOwedBy();

        expense.setPaidBy(this.userRepo.getUserMap().get(paidBy));
        expense.setAmount(expenseAmount);
        for(String uId: allUsers) {
            if(!paidBy.equals(uId)) {
                owed.add(new OwedBy(uId, amountDivided));
            }
        }
    }
}
