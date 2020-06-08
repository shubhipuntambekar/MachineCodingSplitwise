package com.splitwise;

import com.splitwise.models.expense.ExpenseType;
import com.splitwise.models.split.Split;
import com.splitwise.models.split.SplitExactAmount;
import com.splitwise.models.split.SplitPercent;
import com.splitwise.models.user.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
	// write your code here
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        SplitwiseHelper splitwiseHelper = new SplitwiseHelper();
        splitwiseHelper.addUser(new User("Alex", "alex@hotmail.com", "3840948323"));
        splitwiseHelper.addUser(new User("John", "john@hotmail.com", "4792747798"));
        splitwiseHelper.addUser(new User("Marina", "marina@hotmail.com", "4327686643"));
        splitwiseHelper.addUser(new User("Jason", "jason@hotmail.com", "4277916321"));
        splitwiseHelper.addUser(new User("Lizzy", "liz@hotmail.com", "3217821822"));
        splitwiseHelper.addUser(new User("Hannah", "hannah@hotmail.com", "1239186482"));
        splitwiseHelper.addUser(new User("Jackson", "jack@hotmail.com", "2187917236"));

        System.out.println("Welcome to Splitwise");
        System.out.println("Here's the list of users in your contact:");
        splitwiseHelper.displayUsers();
        while (true) {
            System.out.print("Do you want to create Expense? (Y/N): ");
            String response = bufferedReader.readLine();
            if (response.equals("Y") || response.equals("y")) {
                System.out.print("Enter expense title: ");
                String expenseName = bufferedReader.readLine();
                System.out.print("Enter space separated user id's of users to share expense with: ");
                String userList = bufferedReader.readLine();
                String[] users = userList.split(" ");
                System.out.print("Enter the id of person who paid the amount initially: ");
                String paidBy = bufferedReader.readLine();
                System.out.print("Enter amount paid by " + paidBy + " initially: ");
                Double amount = Double.parseDouble(bufferedReader.readLine());
                System.out.print("How do you wish to split your expense? (EXACT_SHARE/PERCENT): ");
                String expenseType = bufferedReader.readLine();
                ArrayList<Split> splits = new ArrayList<>();

                switch (expenseType) {
                    case "EXACT_SHARE":
                        for (int i = 0; i < users.length; i++) {
                            System.out.print("Enter " + users[i] + "'s Share: ");
                            double share = Double.parseDouble(bufferedReader.readLine());
                            splits.add(new SplitExactAmount(splitwiseHelper.userHashMap.get(users[i]), share));
                        }
                        splitwiseHelper.addExpense(expenseName, ExpenseType.EXACT_AMOUNT_SHARE, paidBy, amount, splits);
                        break;
                    case "PERCENT":
                        for (int i = 0; i < users.length; i++) {
                            System.out.print("Enter " + users[i] + "'s Percent: ");
                            double percent = Double.parseDouble(bufferedReader.readLine());
                            splits.add(new SplitPercent(splitwiseHelper.userHashMap.get(users[i]), percent));
                        }
                        splitwiseHelper.addExpense(expenseName, ExpenseType.PERCENT_SHARE, paidBy, amount, splits);
                        break;
                }
                splitwiseHelper.showBalance();
                System.out.print("Do you wish to settle balance with a user? (Y/N): ");
                String clearBal = bufferedReader.readLine();
                if(clearBal.equals("Y") || clearBal.equals("y")){
                    System.out.print("Enter your user id: ");
                    String user1 = bufferedReader.readLine();
                    System.out.print("Enter other user's user id to settle balance: ");
                    String user2 = bufferedReader.readLine();
                    if(splitwiseHelper.clearBalance(user1,user2)){
                        System.out.println("Balance settled.");
                    }else{
                        System.out.println("Sorry, no balance found with the user.");
                    }
                }
            }else {
                break;
            }

        }
    }
}
