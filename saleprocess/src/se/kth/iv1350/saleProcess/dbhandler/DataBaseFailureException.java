package se.kth.iv1350.saleProcess.dbhandler;


/**
 * An exception class which is thrown when a database failure has occurred.
 */
public class DataBaseFailureException extends RuntimeException {

    /**
     * Creates an instance and logs a message which specifies that a database failure has happened.
     * @param message is the message about the error that is being logged.
     */
    DataBaseFailureException(String message) {
        super(message);
    }
}
