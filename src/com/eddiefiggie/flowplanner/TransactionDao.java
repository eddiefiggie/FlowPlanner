package com.eddiefiggie.flowplanner;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TransactionDao implements Dao<Transaction> {

    private List<Transaction> transactions = new ArrayList<>();

    public TransactionDao() {
        // A generic transaction pipeline for the cash flow planning output.
    }

    public TransactionDao(String path) {

        try {
                CSVHandler csvData = new CSVHandler(path, ',', 2);
                int data = csvData.getData().size();
                int count = 0;

                while(count < data) {
                    String description = csvData.getData().get(count++);
                    double amount = Double.parseDouble(csvData.getData().get(count++));
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate date = LocalDate.parse(csvData.getData().get(count++),formatter);
                    int frequency = Integer.parseInt(csvData.getData().get(count++));
                    int transactionType = Integer.parseInt(csvData.getData().get(count++));
                    count++;

                    Transaction transaction = new Transaction(description, amount, date, frequency, transactionType);
                    this.transactions.add(transaction);
                }
        }
        catch (IOException e) {
            System.out.println("File not found.");
        }
    }

    @Override
    public Optional<Transaction> get(long id) {
        return Optional.ofNullable(transactions.get((int) id));
    }

    @Override
    public List<Transaction> getAll() {
        return transactions;
    }

    @Override
    public void save(Transaction transaction, String path) {
        // Not used
    }

    @Override
    public void update(Transaction transaction, String[] params) {
        /*transaction.setDescription(Objects.requireNonNull(
                params[0], "Description cannot be null"));
        transaction.setAmount(double.requireNonNull(
                params[1], "Amount cannot be null"));
        transaction.setDate(Objects.requireNonNull(
                params[2], "Date cannot be null"));

        transaction.add(transaction); */
    }

    @Override
    public void delete(Transaction transaction, String path) {
        // not used
    }
}
