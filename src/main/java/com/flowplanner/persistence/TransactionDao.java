package com.flowplanner.persistence;

import com.opencsv.bean.CsvToBeanBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class TransactionDao implements Dao<Transaction> {

    private List<Transaction> transactions = new ArrayList<>();

    public TransactionDao() {
        // TODO Create appropriate file mapping, and exception handling.
        List<Transaction> beans = new CsvToBeanBuilder(FileReader("/home/eddiefiggie/IdeaProjects/FlowPlanner/data/transaction-data.csv"))
                .withType(Transaction.class).build().parse();

        this.transactions = beans;
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
        transaction.setName(Objects.requireNonNull(
                params[0], "Name cannot be null"));
        transaction.setEmail(Objects.requireNonNull(
                params[1], "Email cannot be null"));

        transaction.add(transaction);
    }

    @Override
    public void delete(Transaction transaction) {
        transactions.remove(transaction);
    }

}
