package se.kth.iv1350.saleProcess.util;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A singleton class that creates an instance the first time it is called upon.
 * It is used for logging all the information concerning exceptions that has been thrown.
 */
public class ExceptionLogger {
    private static final ExceptionLogger LOGGER = new ExceptionLogger();
    private String exceptionLog = "System errors:\n\n";

    /**
     * A constructor only used to create the instance LOGGER.
     */
    private ExceptionLogger() {
    }

    /**
     * @return is the only instance of this singleton.
     */
    public static ExceptionLogger getInstance() {
        return LOGGER;
    }

    /**
     * This method handles the logging part when an exception has been thrown.
     * @param message is the message from the exception which has been thrown.
     */
    public void log(String message) {
        StringBuilder builder = new StringBuilder();
        LocalDateTime timeOfException = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        builder.append(this.exceptionLog);
        builder.append(message + "\n");
        builder.append(timeOfException.format(formatter) + "\n\n");

        this.exceptionLog = builder.toString();
    }

    /**
     * @return Returns the <code>String<code/> exceptionLog.
     */
    public String getExceptionLog() {
        return exceptionLog;
    }
}
