package se.kth.iv1350.saleProcess.dbhandler;

import se.kth.iv1350.saleProcess.model.Receipt;

/**
 * This class handles all the printer operations.
 */

public class Printer {

    /**
     * Creates a new instance.
     */
     Printer(){

    }

    /**
     * Prints the receipt of the finalized sale.
     *
     * @param receipt The <code>Receipt<code/> to print.
     */
     public void printReceipt(Receipt receipt){
           System.out.println(receipt);
     }
}
