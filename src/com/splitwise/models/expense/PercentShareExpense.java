package com.splitwise.models.expense;

import com.splitwise.models.split.Split;
import com.splitwise.models.user.User;

import java.util.ArrayList;

public class PercentShareExpense extends Expense{
    public PercentShareExpense(String expenseName, User paidBy, double amount, ArrayList<Split> splits) {
        super(expenseName, paidBy, amount, splits);
    }
}
