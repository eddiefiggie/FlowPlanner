package com.flowplanner.persistence;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TransactionDao implements Dao<Transaction> {

    private List<Transaction> transactions = new ArrayList<>(); // TODO test without arraylist


    public TransactionDao(String path) {

        try {

            FileReader fr = new FileReader(path);

            transactions = new CsvToBeanBuilder<Transaction>(fr)
                    .withType(Transaction.class)
                    .build()
                    .parse();

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
