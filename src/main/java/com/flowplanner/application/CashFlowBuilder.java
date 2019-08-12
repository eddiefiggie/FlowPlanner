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

    public void transactionAligner(Transaction transaction, boolean compare) {

        if (compare) {
            Transaction newTransaction = new Transaction();
            newTransaction.setDescription(transaction.getDescription());
            newTransaction.setAmount(transaction.getAmount());
            newTransaction.setDate(transaction.getDate());
            newTransaction.setFrequency(transaction.getFrequency());
            newTransaction.setTransactionType(transaction.getTransactionType());

            if (isInRange(newTransaction)) {
                addTransaction(newTransaction);
                newTransaction = createNewTransaction(newTransaction);
                compare = compareToEndDate(newTransaction);
                transactionAligner(newTransaction, compare);

            }
            else {
                newTransaction = createNewTransaction(newTransaction);
                compare = compareToEndDate(newTransaction);
                transactionAligner(newTransaction, compare);
            }


        }
    }

    public Transaction createNewTransaction(Transaction transaction) {
        Transaction newTransaction = transaction;

        if(newTransaction.getTransactionType() == 1) {
            newTransaction.setDate(newTransaction.getDate().plusMonths(newTransaction.getFrequency()));
        }
        else if(newTransaction.getTransactionType() == 2) {
            newTransaction.setDate((newTransaction.getDate().plusDays(newTransaction.getFrequency())));
        }
        return newTransaction;
    }

    public boolean compareToEndDate(Transaction transaction) {
        boolean compare = false;
        if (transaction.getDate().isBefore(this.endDate) || transaction.getDate().isEqual(this.endDate)) {
            compare = true;
        }
        return compare;
    }
}
