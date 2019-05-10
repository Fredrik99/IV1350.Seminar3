package se.kth.iv1350.saleProcess.controller;

/**
 * This class is thrown when a system operation has had a generic failure.
 */
public class OperationFailedException extends Exception {

    /**
     * Creates an instance when a system operation has failed.
     *
     * @param message is the exception message.
     * @param exception is the exception that caused this exception.
     */
     OperationFailedException(String message, Exception exception){
        super(message, exception);
    }
}
