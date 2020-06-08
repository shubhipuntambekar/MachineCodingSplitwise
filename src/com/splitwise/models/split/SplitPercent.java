package com.splitwise.models.split;

import com.splitwise.models.user.User;

public class SplitPercent extends Split {
    private double percent;
    public SplitPercent(User user, double percent) {
        super(user);
        this.percent = percent;
    }

    public double getPercent() {
        return percent;
    }
}
