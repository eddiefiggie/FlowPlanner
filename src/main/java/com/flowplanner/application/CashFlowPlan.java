package com.flowplanner.application;

import com.flowplanner.persistence.Transaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CashFlowPlan {

    private List<Transaction> cashFlowPlan = new ArrayList<>();
    private LocalDate startDate;
    private LocalDate endDate;

    public CashFlowPlan(LocalDate start, LocalDate end) {
        this.startDate = start;
        this.endDate = end;
    }

    public boolean isInRange(Transaction transaction) {
        boolean isInRange = false;
        LocalDate transactionDate = transaction.getDate();

        // trandactionDate is greater than 0 if it follows start or end date
        // a value of 0 means it is the same date
        int differenceToStart = transactionDate.compareTo(this.startDate);
        int differenceToEnd = transactionDate.compareTo(this.endDate);

        if (differenceToStart >= 0 && differenceToEnd <= 0) {
            isInRange = true;
        }
        return isInRange;
    }

    public void addTranscaction(Transaction transaction) {
        this.cashFlowPlan.add(transaction);
    }
}
