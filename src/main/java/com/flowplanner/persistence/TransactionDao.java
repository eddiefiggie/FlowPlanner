package com.flowplanner.persistence;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TransactionDao implements Dao<Transaction> {

    private List<Transaction> transactions = new ArrayList<>();

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
                double amount = Double.parseDouble(csvRecord.get("amount"));

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(csvRecord.get("date"),formatter);

                int transactionType = Integer.parseInt(csvRecord.get("transactionType"));
                int frequency = Integer.parseInt(csvRecord.get("frequency"));

                Transaction transaction = new Transaction(description, amount, date, transactionType, frequency);

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

        this.transactions.add(transaction);

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(path));
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                     .withHeader("description", "amount", "date"))
        ) {
            int counter = 0;
            for (Transaction trans : this.transactions) {

                String description = this.transactions.get(counter).getDescription();
                double amount = this.transactions.get(counter).getAmount();

                String stringDate = this.transactions.get(counter).getDate().toString();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(stringDate, formatter);
                int transactionType = this.transactions.get(counter).getTransactionType();
                int frequency = this.transactions.get(counter).getFrequency();


                csvPrinter.printRecord(description, amount, date, transactionType, frequency);
                counter++;
            }
            csvPrinter.flush();
        }
        catch(IOException e) {
            // handle exception
        }
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

        transactions.remove(transaction);

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(path));
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                     .withHeader("description", "amount", "date", "frequency", "transactionType"))
        ) {
            int counter = 0;
            for (Transaction trans : this.transactions) {
                if(trans != null) {
                    String description = this.transactions.get(counter).getDescription();
                    double amount = this.transactions.get(counter).getAmount();

                    String stringDate = this.transactions.get(counter).getDate().toString();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate date = LocalDate.parse(stringDate, formatter);
                    int transactionType = this.transactions.get(counter).getTransactionType();
                    int frequency = this.transactions.get(counter).getFrequency();

                    csvPrinter.printRecord(description, amount, date, transactionType, frequency);
                    counter++;
                }

            }
            csvPrinter.flush();


        }
        catch(IOException e) {
            // handle exception
        }

    }

}
