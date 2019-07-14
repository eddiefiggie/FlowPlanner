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

        // Showing all transactions
        displayAll(transactions);

        editTransactions(transactions, csvPath.getCsvFilePath());

    }

    private static void displayAll(Dao<Transaction> transactions) {
        int counter = 0;
        for(Transaction trans : transactions.getAll()) {
            System.out.println(transactions.getAll().get(counter));
            counter++;
        }
    }

    private static void editTransactions(Dao<Transaction> transactions, String csvPath) {

        int editType = askForEditType(); // 1 is edit, 2 is delete


        if(editType == 1) {
            displayAll(transactions);
            String searchDescription = askForDescriptionSearch();
            // EDIT CODE
        }
        else if(editType == 2) {
            displayAll(transactions);
            String searchDescription = askForDescriptionSearch();
            for(Transaction search : transactions.getAll()) {

                if(search.getDescription().equalsIgnoreCase(searchDescription)) {
                    // need delete code
                }
                else {
                    System.out.println("No transaction with that description was found.");
                }

            }

        }
        else if (editType == 3) {
            transactions.save(createTransaction(), csvPath);
        }

    }

    private static int askForEditType() {
        System.out.print("Select modification type. [1] Edit, [2] Delete, or [3] Create: ");
        Scanner input = new Scanner(System.in);
        int editType = input.nextInt();
        return editType;
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

    private static String askForDescriptionSearch() {
        System.out.println("[SEARCH TRANSACTIONS]");
        String searchTerm = askForDescription();
        return searchTerm;

    }


}
