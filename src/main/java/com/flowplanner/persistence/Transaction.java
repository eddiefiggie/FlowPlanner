package com.flowplanner.persistence;

import com.opencsv.bean.CsvBindByName;

import java.time.LocalDate;

public class Transaction {

    @CsvBindByName
    private String description;

    @CsvBindByName
    private double amount;

    @CsvBindByName
    private LocalDate date;

    public Transaction() {

    }

    public Transaction(String description, double amount, LocalDate date) {
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void add(Transaction transaction) {
    }
}
