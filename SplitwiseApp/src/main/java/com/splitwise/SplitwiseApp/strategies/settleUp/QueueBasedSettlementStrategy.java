package com.splitwise.SplitwiseApp.strategies.settleUp;

import com.splitwise.SplitwiseApp.models.Expense;
import com.splitwise.SplitwiseApp.models.Transaction;
import com.splitwise.SplitwiseApp.models.User;

import java.util.*;

public class QueueBasedSettlementStrategy implements SettleUpStrategy {
    class Record {
        User user;
        int pendingAmount;

        public Record(User user, int pendingAmount) {
            this.user = user;
            this.pendingAmount = pendingAmount;
        }
    }
    @Override
    public List<Transaction> settleUp(List<Expense> expenses) {
        Map<User, Integer> extraMoney = new HashMap<>();
        for(Expense expense: expenses) {
            User paidBy = expense.getPaidBy();
            if(!extraMoney.containsKey(paidBy)) {
                extraMoney.put(paidBy, 0);
            }
            extraMoney.put(paidBy, extraMoney.getOrDefault(paidBy, 0) + expense.getAmount());
        }

        Queue<Record> negativeQueue = new ArrayDeque<>();
        Queue<Record> positiveQueue = new ArrayDeque<>();

        for (User user: extraMoney.keySet()) {
            if (extraMoney.get(user) < 0) {
                negativeQueue.add(new Record(user, extraMoney.get(user)));
            } else if (extraMoney.get(user) > 0) {
                positiveQueue.add(new Record(user, extraMoney.get(user)));
            }
        }

        List<Transaction> transactions = new ArrayList<>();

        while (!positiveQueue.isEmpty() && !negativeQueue.isEmpty()) {
            Record firstNegative = negativeQueue.remove();
            Record firstPostive = positiveQueue.remove();

            if (firstPostive.pendingAmount > Math.abs(firstNegative.pendingAmount)) {
                transactions.add(
                        new Transaction(firstNegative.user, firstPostive.user, Math.abs(firstNegative.pendingAmount))
                );
                positiveQueue.add(new Record(firstPostive.user, firstPostive.pendingAmount - Math.abs(firstNegative.pendingAmount)));
            } else {
                transactions.add(
                        new Transaction(firstNegative.user, firstPostive.user, firstPostive.pendingAmount)
                );
                negativeQueue.add(new Record(firstNegative.user, firstNegative.pendingAmount + firstPostive.pendingAmount));
            }
        }

        return transactions;
    }
}
