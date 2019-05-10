package se.kth.iv1350.saleProcess.dbhandler;

/**
 * This exception class is thrown when a user is trying to add
 * an item that does not exist in the inventory.
 */
public class InventorySystemException extends Exception{

    /**
     * Creates an instance and logs a message which specifies
     * which itemID that does not exist in the inventory.
     *
     * @param message is the message that is being logged.
     */
     InventorySystemException(String message){
        super(message);
    }
}
