package com.expensetracker.app.domain;

import org.springframework.stereotype.Component;

import com.expensetracker.app.domain.exception.InvalidExpenseException;

@Component
public class ExpensePolicy {

  public void validateExpenseCreation(CreateExpenseCommand command) {
    if(command.getAmount() <= 0) {
        throw new InvalidExpenseException("Amount must be positive");
    }
  }

}
