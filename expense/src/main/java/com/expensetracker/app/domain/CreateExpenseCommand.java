package com.expensetracker.app.domain;

import java.time.LocalDate;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
public class CreateExpenseCommand {
    private final String userId;
    private final String categoryId;
    private final double amount;
    private final LocalDate date;
    private final String notes;
    private final Long catgId;
    
    public CreateExpenseCommand(String userId, String categoryId, double amount, LocalDate date, String notes, Long catId) {
        this.userId = userId;
        this.categoryId = categoryId;
        this.amount = amount;
        this.date = date;
        this.notes = notes;
        this.catgId = catId;
    }

    public String getUserId() {
        return userId;
    }



    public String getCategoryId() {
        return categoryId;
    }



    public double getAmount() {
        return amount;
    }



    public LocalDate getDate() {
        return date;
    }



    public String getNotes() {
        return notes;
    }

    public Long getCatgId() {
        return catgId;
    }

    

}
