package com.flowplanner.persistence;

import java.time.LocalDate;
import java.util.Scanner;

public class Transaction {

    private String description;

    private double amount;

    private LocalDate date;

    private int transactionType;

    private int frequency;

    public Transaction(String description, double amount, LocalDate date, int frequency, int type) {
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.transactionType = type;
        this.frequency = frequency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(int transactionType) {
        this.transactionType = transactionType;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public void add(Transaction transaction) {
    }

    @Override
    public String toString() {
        String string;
        string = "{description: " + getDescription() + ", amount: " + getAmount()
                + ", date: " + getDate() + ", type: " + getTransactionType()
                + ", frequency: " + getFrequency() + "}";
        return string;
    }
}
