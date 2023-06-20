package com.codecool.seasonalproductdiscounter.service.logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileLogger extends LoggerBase {

    private final String logFile;

    public FileLogger(String logFile) {
        this.logFile = logFile;
    }

    @Override
    protected void logMessage(String message, String type) {
        String entry = createLogEntry(message, type);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true))) {
            writer.write(entry);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
