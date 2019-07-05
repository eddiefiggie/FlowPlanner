package com.flowplanner.application;

import com.flowplanner.persistence.Dao;
import com.flowplanner.persistence.DataPathManager;
import com.flowplanner.persistence.Transaction;
import com.flowplanner.persistence.TransactionDao;

import java.util.List;
import java.util.Scanner;

public class flowplannerCLI {

    public static void main(String[] args) {

        DataPathManager csvPath = new DataPathManager();

        if(csvPath.getCsvFilePath() == null) {
            csvPath.setCsvFilePath(askForCsvPath());
        }

        Dao<Transaction> importedData = new TransactionDao(csvPath.getCsvFilePath());

        List<Transaction> transactions = importedData.getAll();

        System.out.println(transactions.get(0).toString());
        System.out.println(transactions.get(1).toString());

    }

    private static String askForCsvPath() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the path to your CSV file: ");
        String path;
        path = input.next();
        return path;
    }
}
