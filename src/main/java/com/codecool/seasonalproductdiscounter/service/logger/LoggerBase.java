package com.codecool.seasonalproductdiscounter.service.logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class LoggerBase implements Logger {
    public void logInfo(String message) {
        logMessage(message, "INFO");
    }

    public void logError(String message) {
        logMessage(message, "ERROR");
    }

    protected static String createLogEntry(String message, String type) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        return String.format("[%s] %s: %s", timestamp, type, message);
    }

    protected abstract void logMessage(String message, String type);
}

