package com.codecool.seasonalproductdiscounter.service.logger;

public class ConsoleLogger extends LoggerBase {
    @Override
    protected void logMessage(String message, String type) {
        String entry = createLogEntry(message, type);
        System.out.println(entry);
    }
}

