package com.flowplanner.application;

import com.flowplanner.persistence.Dao;
import com.flowplanner.persistence.DataPathManager;
import com.flowplanner.persistence.Transaction;
import com.flowplanner.persistence.TransactionDao;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class flowplannerCLI {

    public static void main(String[] args) {

        final String PROPERTIES = "flowplanner.properties";
        DataPathManager csvPath = new DataPathManager(PROPERTIES);
        File properties = new File(PROPERTIES);

        if(properties.exists()) {
            csvPath.loadCsvFilePath();
        }
        else {
            csvPath.writeCsvFilePath(askForCsvPath());
        }

        Dao<Transaction> importedData = new TransactionDao(csvPath.getCsvFilePath());

        List<Transaction> transactions = importedData.getAll();

        System.out.println(transactions.get(0).getDescription());
        System.out.println(transactions.get(0).getAmount());
        System.out.println(transactions.get(0).getDate());

    }

    private static String askForCsvPath() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the path to your CSV file: ");
        String path = input.next();
        return path;
    }
}
