package com.flowplanner.persistence;

import java.io.*;
import java.util.Properties;

public class DataPathManager {

    final private String PROPERTIES_FILE = "flowplanner.properties";
    final private String PROPERTIES_KEY = "CSV_FILE_PATH";

    private String properties;
    private Properties prop;
    private String csvFilePath;

    public DataPathManager() {
        loadCsvFilePath();
    }

    public String getCsvFilePath() {
        return this.csvFilePath;
    }

    public void setCsvFilePath(String path) {
        try (OutputStream output = new FileOutputStream(PROPERTIES_FILE)) {

            this.prop = new Properties();

            this.prop.setProperty(PROPERTIES_KEY, path);
            this.prop.store(output, null);

        } catch (IOException io) {
            io.printStackTrace();
        }
        this.csvFilePath = prop.getProperty(PROPERTIES_KEY);
    }

    private void loadCsvFilePath() {
        try (InputStream input = new FileInputStream(PROPERTIES_FILE)) {
            this.prop = new Properties();
            this.prop.load(input);

            this.csvFilePath = prop.getProperty(PROPERTIES_KEY);

        } catch (IOException ex) {
            System.out.println("There is no file path currently set.");
            this.csvFilePath = null;
        }
    }
}
