package com.splitwise.models.split;

import com.splitwise.models.user.User;

public class SplitExactAmount extends Split{
    public SplitExactAmount(User user, double amount) {
        super(user);
        this.amount = amount;
    }

}
