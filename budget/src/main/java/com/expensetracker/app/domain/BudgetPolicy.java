package com.expensetracker.app.domain;

import java.math.BigDecimal;

public class BudgetPolicy {

    public static BudgetStatus calculateStatus(
            BigDecimal limit,
            BigDecimal spent) {

        if (spent.compareTo(limit) > 0) {
            return BudgetStatus.EXCEEDED;
        }

        if (spent.compareTo(limit.multiply(BigDecimal.valueOf(0.8))) >= 0) {
            return BudgetStatus.WARNING;
        }

        return BudgetStatus.OK;
    }

}
