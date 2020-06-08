package com.splitwise.models.expense;

import com.splitwise.models.split.Split;
import com.splitwise.models.split.SplitPercent;
import com.splitwise.models.user.User;

import java.util.ArrayList;

public class ExpenseFactory {
    public static Expense createExpense(String expenseName, ExpenseType expenseType, User paidBy, double amount, ArrayList<Split> splits){
        switch (expenseType){
            case EXACT_AMOUNT_SHARE :
                return new ExactAmountShareExpense(expenseName,paidBy,amount,splits);
            case PERCENT_SHARE:
                for (Split split : splits){
                    SplitPercent splitPercent = (SplitPercent) split;
                    split.setAmount((amount * splitPercent.getPercent())/100.0);
                }
                return new PercentShareExpense(expenseName,paidBy,amount,splits);
            default:
                return null;
        }
    }
}
