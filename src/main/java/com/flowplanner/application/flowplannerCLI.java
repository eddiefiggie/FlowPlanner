package com.flowplanner.application;

import com.flowplanner.persistence.Dao;
import com.flowplanner.persistence.DataPathManager;
import com.flowplanner.persistence.Transaction;
import com.flowplanner.persistence.TransactionDao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class flowplannerCLI {

    public static void main(String[] args) {

        DataPathManager csvPath = new DataPathManager();

        if(!csvPath.pathExists()) {
            csvPath.setCsvFilePath(askForCsvPath());
        }

        Dao<Transaction> transactions = new TransactionDao(csvPath.getCsvFilePath());

        transactions.save(createTransaction(), csvPath.getCsvFilePath());

        int counter = 0;
        for(Transaction trans : transactions.getAll()) {
            System.out.println(transactions.getAll().get(counter));
            counter++;
        }
    }

    private static String askForCsvPath() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the path to your CSV file: ");
        String path;
        path = input.next();
        return path;
    }

    private static Transaction createTransaction() {
        String description = askForDescription();
        double amount = askForAmount();
        LocalDate date = askForDate();

        Transaction transaction = new Transaction(description, amount, date);
        return transaction;
    }

    private static String askForDescription() {
        System.out.print("Briefly describe this transaction: ");
        Scanner input = new Scanner(System.in);
        String transactionDescription = input.nextLine();
        return transactionDescription;
    }

    private static double askForAmount() {
        System.out.print("Enter the transaction dollar amount: ");
        Scanner input = new Scanner(System.in); // TODO exception handling on bad input (amount)
        double transactionAmount = input.nextDouble();
        return transactionAmount;
    }

    private static LocalDate askForDate() {
        System.out.print("Enter the transaction date [yyyy-mm-dd]: ");
        Scanner input = new Scanner(System.in); // TODO exception handling on bad input (date)
        String StringDate = input.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");  // TODO Centralize formatter
        LocalDate transactionDate = LocalDate.parse(StringDate, formatter);

        return transactionDate;
    }
}
