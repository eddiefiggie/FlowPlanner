package com.flowplanner.application;

import com.flowplanner.persistence.Transaction;

import java.time.LocalDate;
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
        this.cashFlowPlan.add(transaction);
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
                addTransaction(newTransaction);
            }

            compare = compareToEndDate(advanceDate(newTransaction));
            transactionAligner(newTransaction, compare, advanceDate(newTransaction));
        }
    }

    public LocalDate advanceDate(Transaction transaction) {
        LocalDate date = null;

        if(transaction.getTransactionType() == 1) {
            date = transaction.getDate().plusMonths(transaction.getFrequency());
        }
        else if(transaction.getTransactionType() == 2) {
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
}
