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

import java.util.ArrayList;
import java.util.List;

@Component
@NoArgsConstructor
public class ExactSplitStrategy implements SplitStrategy {

    private CreateExpenseRequest request;
    private Expense expense;

    private UserRepo userRepo;

    public ExactSplitStrategy(CreateExpenseRequest request, Expense expense, UserRepo userRepo) {
        this.request = request;
        this.expense = expense;
        this.userRepo = userRepo;
    }

    @Override
    public void calculateExpense() {

        List<OwedBy> owedByList = expense.getOwedBy();

        String paidByRequestUser = request.getPaidBy();
        List<String> allUsers = request.getUsers();
        List<Integer> amountsList = new ArrayList<>();
        if(request.getExactAmounts().isPresent())
            amountsList = request.getExactAmounts().get();
        int amount = request.getAmount();
        expense.setAmount(amount);
        expense.setPaidBy(userRepo.getUserMap().get(paidByRequestUser));


        for(int idx = 0; idx < allUsers.size(); idx++) {
            String uId = allUsers.get(idx);
            int amt = amountsList.get(idx);
            if(!paidByRequestUser.equals(uId)) {
                owedByList.add(new OwedBy(uId, amt));
            }
        }

    }
}
