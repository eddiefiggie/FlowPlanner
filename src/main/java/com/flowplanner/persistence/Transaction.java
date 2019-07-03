package com.flowplanner.persistence;

import com.opencsv.bean.CsvBindByName;

public class Transaction {

    @CsvBindByName
    private String description;

    @CsvBindByName
    private double amount;

    @CsvBindByName
    private String date; //TODO: Figure out "date type" treatment

    public Transaction() {

    }

    public Transaction(String description, double amount, String date) {
        this.description = description;
        this.amount = amount;
        this.date = date;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void add(Transaction transaction) {
    }
}
