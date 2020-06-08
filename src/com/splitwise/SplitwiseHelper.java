package com.splitwise;

import com.splitwise.models.expense.Expense;
import com.splitwise.models.expense.ExpenseFactory;
import com.splitwise.models.expense.ExpenseType;
import com.splitwise.models.split.Split;
import com.splitwise.models.user.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SplitwiseHelper {
    ArrayList<Expense> expenses;
    HashMap<String, User> userHashMap;
    HashMap<String, HashMap<String, Double>> balanceSheet;

    public SplitwiseHelper() {
        expenses = new ArrayList<Expense>();
        userHashMap = new HashMap<String, User>();
        balanceSheet = new HashMap<String, HashMap<String, Double>>();
    }
    public void addUser(User user) {
        userHashMap.put(user.getUserId(), user);
        balanceSheet.put(user.getUserId(), new HashMap<String, Double>());
    }
    public void displayUsers() {
        int i = 1;
        Iterator<Map.Entry<String, User>> entries = userHashMap.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, User> entry = entries.next();
            System.out.println(i+". "+entry.getValue().getUserName() + ": (" + entry.getKey() + ")");
            i++;
        }
    }
    public void addExpense(String expenseName, ExpenseType expenseType, String paidBy, double amount, ArrayList<Split> splits) {
        Expense expense = ExpenseFactory.createExpense(expenseName, expenseType, userHashMap.get(paidBy), amount, splits);
        expenses.add(expense);
        for (Split split : expense.getSplits()) {
            String paidTo = split.getUser().getUserId();
            HashMap<String, Double> balances = balanceSheet.get(paidBy);
            if (!balances.containsKey(paidTo)) {
                balances.put(paidTo, 0.0);
            }
            balances.put(paidTo, balances.get(paidTo) + split.getAmount());
            balances = balanceSheet.get(paidTo);
            if (!balances.containsKey(paidBy)) {
                balances.put(paidBy, 0.0);
            }
            balances.put(paidBy, balances.get(paidBy) - split.getAmount());
        }
    }
    public void showBalance() {
        boolean isEmpty = true;
        for (Map.Entry<String, HashMap<String, Double>> allBalance : balanceSheet.entrySet()) {
            for (Map.Entry<String, Double> balance : allBalance.getValue().entrySet()) {
                if (balance.getValue() > 0) {
                    isEmpty = false;
                    printBalance(allBalance.getKey(), balance.getKey(), balance.getValue());
                }
            }
        }
        if (isEmpty) {
            System.out.print("Everything is clear.");
        }
    }
    public void printBalance(String user1, String user2, double amount) {
        String userName1 = userHashMap.get(user1).getUserName();
        String userName2 = userHashMap.get(user2).getUserName();
        if (amount < 0) {
            System.out.println(userName1 + " owes " + userName2 + " " + Math.abs(amount) + "Rs.");
        } else if (amount > 0) {
            System.out.println(userName2 + " owes " + userName1 + " " + Math.abs(amount) + "Rs.");
        }
    }

    public boolean clearBalance(String user1, String user2) {
        HashMap<String,Double> user1balances = balanceSheet.get(user1);
        HashMap<String,Double> user2balances = balanceSheet.get(user2);
        if(user1balances.containsKey(user2) && user2balances.containsKey(user1)){
            user1balances.put(user2,0.0);
            user2balances.put(user1,0.0);
            return true;
        }
        return false;
    }
}
