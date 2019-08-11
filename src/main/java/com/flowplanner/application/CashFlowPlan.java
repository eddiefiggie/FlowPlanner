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

    public void addTranscaction(Transaction transaction) {
        this.cashFlowPlan.add(transaction);
    }
}
