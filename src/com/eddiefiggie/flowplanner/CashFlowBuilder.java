package com.eddiefiggie.flowplanner;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CashFlowBuilder {

    private List<Transaction> cashFlowPlan = new ArrayList<>();
    private LocalDate startDate;
    private LocalDate endDate;

    public CashFlowBuilder(LocalDate start, LocalDate end) {
        this.startDate = start;
        this.endDate = end;
    }

    public List<Transaction> getAll() {
        return this.cashFlowPlan;
    }

    public boolean isInRange(Transaction transaction) {
        boolean isInRange = false;
        LocalDate transactionDate = transaction.getDate();

        boolean isAfterStart = transactionDate.isAfter(this.startDate);
        boolean isEqualToStart = this.startDate.isEqual(transactionDate);

        boolean isBeforeEnd = transactionDate.isBefore(this.endDate);
        boolean isEqualToEnd = transactionDate.isEqual(this.endDate);

        if (isAfterStart == true || isEqualToStart == true && isBeforeEnd == true || isEqualToEnd == true) {
            isInRange = true;
        }
        return isInRange;
    }

    public void addTransaction(Transaction transaction) {
        if(transaction.getDescription().equals("***** Vernon Pay Check")) {
            addTransactionSpacing(transaction.getDate());
            this.cashFlowPlan.add(transaction);
        }
        else {
            this.cashFlowPlan.add(transaction);
        }
    }

    public void addTransactionSpacing(LocalDate date) {
        for(int counter = 0; counter < 9; counter++) {
            String description = "*DELETE THIS*";
            double amount = 999999;
            int frequency = 0;
            int type = 0;
            Transaction blank = new Transaction(description, amount, date, frequency, type);
            this.cashFlowPlan.add(blank);
        }
    }

    public void transactionAligner(Transaction transaction, LocalDate date) {
        boolean compare;
        if(compareToEndDate(date)) {
            compare = true;
        }
        else {
            compare = false;
        }

        if (compare) {
            Transaction newTransaction = new Transaction();
            newTransaction.setDescription(transaction.getDescription());
            newTransaction.setAmount(transaction.getAmount());
            newTransaction.setDate(date);
            newTransaction.setFrequency(transaction.getFrequency());
            newTransaction.setTransactionType(transaction.getTransactionType());

            if (isInRange(newTransaction)) {
                newTransaction.setDate(date);
                addTransaction(newTransaction);
            }

            // possible issue here?
            compare = compareToEndDate(advanceDate(newTransaction));
            transactionAligner(newTransaction, advanceDate(newTransaction));
        }
    }

    public LocalDate adjustDateForWeekend(LocalDate date, double isNegativeAmount) {
        if(date.getDayOfWeek().getValue() == 7) {
            date = date.minusDays(4);
            return date;
        }
        else if(date.getDayOfWeek().getValue() == 6) {
            date = date.minusDays(3);
            return date;
        }
        else if(date.getDayOfWeek().getValue() == 5 && isNegativeAmount < 1) {
            date = date.minusDays(2);
            return date;
        }
        else if(date.getDayOfWeek().getValue() == 4 && isNegativeAmount < 1) {
            date = date.minusDays(1);
            return date;
        }
        else {
            return date;
        }
    }

    public LocalDate advanceDate(Transaction transaction) {
        LocalDate date = null;

        if(transaction.getTransactionType() == 1) {
            date = transaction.getDate().plusMonths(transaction.getFrequency());
        }
        else if(transaction.getTransactionType() == 2 || transaction.getTransactionType() == 3) {
            date = transaction.getDate().plusDays(transaction.getFrequency());
        }
        return date;
    }

    public boolean compareToEndDate(LocalDate date) {
        boolean compare = false;
        if (date.isBefore(this.endDate) || date.isEqual(this.endDate)) {
            compare = true;
        }
        return compare;
    }

    public void exportPlan() throws FileNotFoundException {
        String outputFileName = "output.csv";
        PrintWriter csvOut =  new PrintWriter(outputFileName);

        for(int count = 0; count < cashFlowPlan.size(); count++) {
            String record = String.format("%s", this.cashFlowPlan.get(count).toString());
            csvOut.printf("%s\n", record);
        }
        csvOut.close();

        System.out.println("Cash flow report generation successful. Created a new: output.csv");
    }
}
