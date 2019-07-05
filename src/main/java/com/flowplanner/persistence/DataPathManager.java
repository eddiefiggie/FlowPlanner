package com.flowplanner.persistence;

import java.io.*;
import java.util.Properties;

public class DataPathManager {

    final private String PROPERTIES_KEY = "CSV_FILE_PATH";

    private String properties;
    private Properties prop;
    private String csvFilePath;

    public DataPathManager(String propertiesName) {
        this.properties = propertiesName;
    }

    public String getCsvFilePath() {
        return this.csvFilePath;
    }

    public void writeCsvFilePath(String path) {
        try (OutputStream output = new FileOutputStream(this.properties)) {

            this.prop = new Properties();

            this.prop.setProperty(PROPERTIES_KEY, path);
            this.prop.store(output, null);

        } catch (IOException io) {
            io.printStackTrace();
        }
        this.csvFilePath = prop.getProperty(PROPERTIES_KEY);
    }

    public void loadCsvFilePath() {
        try (InputStream input = new FileInputStream(this.properties)) {
            this.prop = new Properties();
            this.prop.load(input);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        this.csvFilePath = prop.getProperty(PROPERTIES_KEY);
    }
}
