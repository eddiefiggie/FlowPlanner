package com.flowplanner.application;

import com.flowplanner.persistence.Dao;
import com.flowplanner.persistence.Transaction;
import com.flowplanner.persistence.TransactionDao;

import java.util.List;

public class flowplannerCLI {

    public static void main(String[] args) {
        
        Dao importedData = new TransactionDao();
        List<Transaction> transactions = importedData.getAll();

        System.out.println(transactions.get(0).getDescription());
        System.out.println(transactions.get(0).getAmount());
        System.out.println(transactions.get(0).getDate());

        System.out.println(transactions.get(1).getDescription());
        System.out.println(transactions.get(1).getAmount());
        System.out.println(transactions.get(1).getDate());

    }
}
