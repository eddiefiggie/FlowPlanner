package com.flowplanner.application;

import com.flowplanner.persistence.Transaction;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
        if(transaction.getTransactionType() == 3) {
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

    public void transactionAligner(Transaction transaction, boolean compare, LocalDate date) {

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
            compare = compareToEndDate(advanceDate(newTransaction));
            transactionAligner(newTransaction, compare, advanceDate(newTransaction));
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

    public void exportPlan() {

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("export.csv"));
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                     .withHeader("description", "amount", "date"))
        ) {
            int counter = 0;
            for (Transaction trans : this.cashFlowPlan) {

                String description = this.cashFlowPlan.get(counter).getDescription();
                double amount = this.cashFlowPlan.get(counter).getAmount();

                String stringDate = this.cashFlowPlan.get(counter).getDate().toString();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(stringDate, formatter);

                csvPrinter.printRecord(date, description, amount);
                counter++;
            }
            csvPrinter.flush();
        }
        catch(IOException e) {
            // handle exception
        }
    }
}
