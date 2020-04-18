package com.eddiefiggie.flowplanner;

import java.io.*;
import java.util.Properties;

public class DataPathManager {

    final private String PROPERTIES_FILE = "flowplanner.properties";
    final private String PROPERTIES_KEY = "CSV_FILE_PATH";

    private Properties prop;
    private String csvFilePath;

    public DataPathManager() {
        loadProperties();
    }

    public String getCsvFilePath() {
        return this.csvFilePath;
    }

    public boolean pathExists() {
        boolean pathExists;
        if(this.csvFilePath == null) {
            pathExists = false;
            return pathExists;
        }
        else {
            pathExists = true;
            return pathExists;
        }
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

    private void loadProperties() {
        try (InputStream input = new FileInputStream(PROPERTIES_FILE)) {
            this.prop = new Properties();
            this.prop.load(input);

            this.csvFilePath = prop.getProperty(PROPERTIES_KEY);

        } catch (IOException ex) {
            this.csvFilePath = null;
        }
    }
}
