package com.flowplanner.persistence;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TransactionDao implements Dao<Transaction> {

    private List<Transaction> transactions = new ArrayList<>(); // TODO test without arraylist


    public TransactionDao(String path) {

        try (
                Reader reader = Files.newBufferedReader(Paths.get(path));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                        .withFirstRecordAsHeader()
                        .withIgnoreHeaderCase()
                        .withTrim())
        ){
            for (CSVRecord csvRecord : csvParser) {
                String description = csvRecord.get("description");
                String amount = csvRecord.get("amount");
                String date = csvRecord.get("date");

                Transaction transaction = new Transaction(description, amount, date);

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
    public void save(Transaction transaction) {
        transactions.add(transaction);
    }

    @Override
    public void update(Transaction transaction, String[] params) {
        /*transaction.setDescription(Objects.requireNonNull(
                params[0], "Description cannot be null"));
        transaction.setAmount(double.requireNonNull(
                params[1], "Amount cannot be null"));
        transaction.setDate(Objects.requireNonNull(
                params[2], "Date cannot be null"));

        transaction.add(transaction);*/
    }

    @Override
    public void delete(Transaction transaction) {
        transactions.remove(transaction);
    }

}
