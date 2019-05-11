package se.kth.iv1350.saleProcess.model;

import se.kth.iv1350.saleProcess.util.Amount;

/**
 * A listener interface for receiving notifications about sales.
 * The class that is interested in such notifications implements this interface
 * and the object created with that class is registered with
 * @link se.kth.iv1350.saleProcess.controller.Controller#addSaleObserver(SaleObserver).
 * When a payment is made that object's {@link #newAmountPaid} method is invoked.
 */
public interface SaleObserver {

    /**
     * Invoked when a sale has been finalized and an amount has been paid.
     *
     * @param amountPaid is the amount that has been paid.
     */
    void newAmountPaid(Amount amountPaid);
}
