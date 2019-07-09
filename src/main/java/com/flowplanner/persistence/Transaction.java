package com.flowplanner.persistence;

import java.time.LocalDate;
import java.util.Scanner;

public class Transaction {

    private String description;

    private double amount;

    private LocalDate date;

    public Transaction(String description, double amount, LocalDate date) {
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    private String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    private LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void add(Transaction transaction) {
    }

    @Override
    public String toString() {
        String string;
        string = "{description: " + getDescription() + ", amount: " + getAmount()
                + ", date: " + getDate() + "}";
        return string;
    }
}
