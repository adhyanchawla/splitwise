package com.splitwise.SplitwiseApp.strategies;

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
public class PercentSplitStrategy implements SplitStrategy {

    private CreateExpenseRequest request;

    private UserRepo userRepo;

    private Expense expense;

    public PercentSplitStrategy(CreateExpenseRequest request, Expense expense, UserRepo userRepo) {
        this.request = request;
        this.expense = expense;
        this.userRepo = userRepo;
    }

    @Override
    public void calculateExpense() {
        List<OwedBy> owed = expense.getOwedBy();

        String paidByUser = request.getPaidBy();
        List<String> allUsers = request.getUsers();
        List<Integer> percentages = request.getPercentageList().get();
        int totalAmount = request.getAmount();
        expense.setAmount(totalAmount);
        expense.setPaidBy(userRepo.getUserMap().get(paidByUser));
        System.out.println("Percentages " + percentages);
        for(int idx = 0; idx < allUsers.size(); idx++) {
            String uId = allUsers.get(idx);
            if(!paidByUser.contains(uId)) {
                owed.add(new OwedBy(uId, (totalAmount * percentages.get(idx)) / 100));
            }
        }
    }
}
