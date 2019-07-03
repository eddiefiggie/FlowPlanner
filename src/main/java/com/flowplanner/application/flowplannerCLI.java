package com.flowplanner.application;

import com.flowplanner.persistence.Dao;
import com.flowplanner.persistence.TransactionDao;

public class flowplannerCLI {

    public static void main(String[] args) {
        
        Dao transactions = new TransactionDao();

        System.out.println("This is a test");
        System.out.println(transactions.get(1).toString());
        System.out.println(transactions.get(2).toString());

    }
}
