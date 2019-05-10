package se.kth.iv1350.saleProcess.controller;

/**
 * This class is thrown when a user has entered an invalid identifier.
 */
public class InvalidIdentifierException extends Exception {

    /**
     * Creates an instance with a message that specifies
     * for what identification number the process failed.
     *
     * @param invalidIdentifier is the identifier for which an <code>ItemDTO</code> does not exist in the inventory.
     */
     InvalidIdentifierException(int invalidIdentifier){
        super("There exists no item in registry with identification number " + invalidIdentifier);
    }
}
