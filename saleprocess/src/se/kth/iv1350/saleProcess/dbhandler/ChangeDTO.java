package se.kth.iv1350.saleProcess.dbhandler;

import se.kth.iv1350.saleProcess.util.Amount;

/**
 * This class calculates the amount of change to give back
 * to the customer of the finalized sale.
 */
public final class ChangeDTO {
    private Amount changeAmount;

    /**
     * Create an instance.
     *
     * @param paidAmount The amount the customer paid.
     * @param totalPrice The total price of the sale.
     */
    public ChangeDTO(Amount paidAmount, Amount totalPrice){

        this.changeAmount = new Amount();
        this.changeAmount.subtractAmount(paidAmount, totalPrice);
    }

    /**
     * Returns the amount of the change in form of a <code>String<code/>.
     *
     * @return The returned change amount.
     */
    public String toString(){
        return "" + this.changeAmount;
    }

}
