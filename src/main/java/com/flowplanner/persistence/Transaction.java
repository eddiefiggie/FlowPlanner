package com.flowplanner.persistence;

public class Transaction {

    private String description;

    private String amount;

    private String date;

    public Transaction(String description, String amount, String date) {
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

    private String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    private String getDate() {
        return date;
    }

    public void setDate(String date) {
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
