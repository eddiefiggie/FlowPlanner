package com.flowplanner.persistence;

import java.time.LocalDate;

public class Transaction implements Comparable<Transaction> {

    private String description;

    private double amount;

    private LocalDate date;

    private long transactionType;

    private long frequency;

    public Transaction() {

    }

    public Transaction(String description, double amount, LocalDate date, long frequency, long type) {
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

    public long getTransactionType() {
        return this.transactionType;
    }

    public void setTransactionType(long transactionType) {
        this.transactionType = transactionType;
    }

    public long getFrequency() {
        return this.frequency;
    }

    public void setFrequency(long frequency) {
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

    public int compareTo(Transaction transaction) {
        int comp = this.date.compareTo(transaction.getDate());
        if(comp != 0) {
            return comp;
        }
        else {
            int transactionAmount = (int) transaction.getAmount();
            int baseAmount = (int) this.amount;
            comp = transactionAmount - baseAmount;
            return comp;
        }
        // return this.date.compareTo(transaction.getDate());
    }
}
