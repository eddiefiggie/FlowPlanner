package com.flowplanner.persistence;

import com.opencsv.bean.CsvBindByName;

import java.time.LocalDate;
import java.time.LocalTime;

public class Transaction {

    @CsvBindByName
    private String description;

    @CsvBindByName
    private double amount;

    @CsvBindByName
    private String date;

    public Transaction() {

    }

    public Transaction(String description, double amount, String date) {
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
