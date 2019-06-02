package se.kth.iv1350.saleProcess.model;

/**
 * An exception class which is thrown when a customer is not eligible for a discount.
 */
public class DiscountException extends Exception{

    /**
     * Creates an instance and logs a message that specifies that the current
     * customer identification is not eligible for a discount.
     * @param message is the message that is being logged.
     */
     DiscountException(String message) {
        super(message);
    }
}
