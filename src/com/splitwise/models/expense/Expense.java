package com.splitwise.models.expense;

import com.splitwise.models.split.Split;
import com.splitwise.models.user.User;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Expense {
    private String expenseId;
    private String expenseName;
    private User paidBy;
    private double amount;
    private ArrayList<Split> splits = new ArrayList<Split>();
    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    public Expense(String expenseName, User paidBy, double amount, ArrayList<Split> splits) {
        this.expenseId = "exp00_"+atomicInteger.incrementAndGet();
        this.expenseName = expenseName;
        this.paidBy = paidBy;
        this.amount = amount;
        this.splits = splits;
    }

    public String getExpenseId() {
        return expenseId;
    }

    public String getExpenseName() {
        return expenseName;
    }

    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }

    public User getPaidBy() {
        return paidBy;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public ArrayList<Split> getSplits() {
        return splits;
    }

    public void setSplits(ArrayList<Split> splits) {
        this.splits = splits;
    }
}
