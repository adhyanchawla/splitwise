package com.splitwise.SplitwiseApp.Services.impl;

import com.splitwise.SplitwiseApp.Repositories.ExpenseRepo;
import com.splitwise.SplitwiseApp.Repositories.UserRepo;
import com.splitwise.SplitwiseApp.Services.ExpenseService;
import com.splitwise.SplitwiseApp.SplitFactory.SplitFactory;
import com.splitwise.SplitwiseApp.models.Expense;
import com.splitwise.SplitwiseApp.models.OwedBy;
import com.splitwise.SplitwiseApp.payload.CreateExpenseRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final UserRepo userRepo;


    private final ExpenseRepo expenseRepo;


    public ExpenseServiceImpl(UserRepo userRepo, ExpenseRepo expenseRepo) {
        this.userRepo = userRepo;
        this.expenseRepo = expenseRepo;
    }

    @Override
    public Expense createExpense(CreateExpenseRequest expenseRequest) {
        Expense expense = new Expense();
        expense.setAmount(expenseRequest.getAmount());
        expense.setExpenseType(expenseRequest.getExpenseType());
        String expenseId = UUID.randomUUID().toString();
        expense.setExpenseId(expenseId);
        SplitFactory.getSplitStrategy(expenseRequest, expense, userRepo).calculateExpense();
        this.expenseRepo.getExpenseMap().put(expenseId, expense);
        for (OwedBy owedBy : expense.getOwedBy()) {
            String paidTo = owedBy.getUserId();

            if(!this.expenseRepo.getUserExpenseMap().containsKey(expense.getPaidBy().getUserId())) {
                this.expenseRepo.getUserExpenseMap().put(expense.getPaidBy().getUserId(), new HashMap<>());
            }

            Map<String, Integer> balances = this.expenseRepo.getUserExpenseMap().get(expense.getPaidBy().getUserId());

            if (!balances.containsKey(paidTo)) {
                balances.put(paidTo, 0);
            }
            balances.put(paidTo, balances.get(paidTo) + owedBy.getAmount());


            if(!this.expenseRepo.getUserExpenseMap().containsKey(paidTo)) {
                this.expenseRepo.getUserExpenseMap().put(paidTo, new HashMap<>());
            }
            balances = this.expenseRepo.getUserExpenseMap().get(paidTo);

            if (!balances.containsKey(expense.getPaidBy().getUserId())) {
                balances.put(expense.getPaidBy().getUserId(), 0);
            }
            balances.put(expense.getPaidBy().getUserId(), balances.get(expense.getPaidBy().getUserId()) - owedBy.getAmount());
        }
        return expense;
    }

    @Override
    public List<String> getExpensesByUser(String userId) {
        System.out.println("user id " + userId);
        if(!this.expenseRepo.getUserExpenseMap().containsKey(userId)) {
            return new ArrayList<>();
        }
        List<String> result = new ArrayList<>();
        for(String uId: this.expenseRepo.getUserExpenseMap().get(userId).keySet()) {
            int amount = this.expenseRepo.getUserExpenseMap().get(userId).get(uId);
            if(amount > 0) {
                result.add(uId + " owes " + userId + " " + amount);
            }
        }
        return result;
    }

    @Override
    public List<String> getAllExpenses() {
        List<String> expenseDetails = new ArrayList<>();
        for(String userId: this.expenseRepo.getUserExpenseMap().keySet()) {
            for(String userId2: this.expenseRepo.getUserExpenseMap().get(userId).keySet()) {
                int amount = this.expenseRepo.getUserExpenseMap().get(userId).get(userId2);
                if(amount > 0) {
                    expenseDetails.add(userId2 + " owes " + userId + " amount " + amount);
                }
            }
        }
        return expenseDetails;
    }
}
