package com.eddiefiggie.flowplanner;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FlowPlannerCLI {

    public static void main(String[] args) throws FileNotFoundException {

        DataPathManager csvPath = new DataPathManager();

        if(!csvPath.pathExists()) {
            csvPath.setCsvFilePath(askForCsvPath());
        }

        Dao<Transaction> transactions = new TransactionDao(csvPath.getCsvFilePath());

        // Interface Launches Here --
        uiBackEnd(transactions, csvPath.getCsvFilePath());

    }

    private static void uiBackEnd(Dao<Transaction> transactions, String csvPath) throws FileNotFoundException {
        int uiSelection = uiFrontEnd();

        while (uiSelection != 6) {
            if (uiSelection == 0) {
                uiSelection = uiFrontEnd();
            } else if (uiSelection == 1) {
                // Not implemented
                System.out.println("This feature is not implemented.");
                uiSelection = 0;
            } else if (uiSelection == 2) {
                displayAll(transactions);
                String searchDescription = askForDescriptionSearch();
                for (Transaction search : transactions.getAll()) {

                    if (search.getDescription().equalsIgnoreCase(searchDescription)) {
                        transactions.delete(search, csvPath);
                        break;
                    }
                }
                uiSelection = 0;
            } else if (uiSelection == 3) {
                transactions.save(createTransaction(), csvPath);
                uiSelection = 0;
            } else if (uiSelection == 4) {
                displayAll(transactions);
                uiSelection = 0;
            } else if (uiSelection == 5) {
                System.out.println("** START OF REPORT DATE **");
                LocalDate start = askForDate();
                System.out.println("** END OF REPORT DATE **");
                LocalDate end = askForDate();

                CashFlowBuilder cashFlowBuilder = new CashFlowBuilder(start, end);

                // Builds a list of transactions in the qualified date range
                for(Transaction trans : transactions.getAll()) {
                    cashFlowBuilder.transactionAligner(trans, trans.getDate());
                }

                for(Transaction trans : cashFlowBuilder.getAll()) {
                    trans.setDate(cashFlowBuilder.adjustDateForWeekend(trans.getDate(), trans.getAmount()));
                }

                // Place transactions in order by date.
                Collections.sort(cashFlowBuilder.getAll());

                // Creates the CSV file
                cashFlowBuilder.exportPlan();

                uiSelection = 0;
            }
        }
    }

    private static int uiFrontEnd() {
        System.out.print("Select modification type. [1] Edit, [2] Delete, " +
                "[3] Create, [4] List All, [5] Build Plan, or [6] Exit: ");

        try {
            Scanner input = new Scanner(System.in);
            int editType = input.nextInt();
            return editType;
            }
        catch(InputMismatchException e) {
            System.out.println(invalidEntry());
            return 0;
        }
    }

    private static void displayAll(Dao<Transaction> transactions) {
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
        int type = askForType();
        int frequency = askForFrequency();

        Transaction transaction = new Transaction(description, amount, date, type, frequency);
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
        Scanner input = new Scanner(System.in);
        double transactionAmount = input.nextDouble();
        return transactionAmount;
    }

    private static int askForType() {
        System.out.print("Select a transaction type, [1] Daily, [2] Monthly: ");
        Scanner input = new Scanner(System.in);
        int type = input.nextInt();
        return type;
    }

    private static int askForFrequency() {
        System.out.print("Based on the type selected, what is the frequency of this transaction: ");
        Scanner input = new Scanner(System.in);
        int frequency = input.nextInt();
        return frequency;
    }

    private static LocalDate askForDate() {
        System.out.print("Enter the date [yyyy-mm-dd]: ");
        Scanner input = new Scanner(System.in);
        String StringDate = input.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate transactionDate = LocalDate.parse(StringDate, formatter);

        return transactionDate;
    }

    private static String askForDescriptionSearch() {
        System.out.println("[SEARCH TRANSACTIONS]");
        String searchTerm = askForDescription();
        return searchTerm;

    }

    private static String invalidEntry() {
        String errorMsg = "Invalid Entry, try again: ";
        return errorMsg;
    }
}
