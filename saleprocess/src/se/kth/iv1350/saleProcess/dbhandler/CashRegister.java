package se.kth.iv1350.saleProcess.dbhandler;

import se.kth.iv1350.saleProcess.util.Amount;

/**
 * This class represents the stores cash register where
 * the sales transactions are taking place and money is beeing handled.
 */

public class CashRegister {

    private Amount amountPresent;

    /**
     * Creates an instance.
     */
    CashRegister(){
        this.amountPresent = new Amount(100000);
        System.out.println("Amount present in the cash register: " + this.amountPresent + " kr\n");
    }

    /**
     * Adds the payment from the customer into the register.
     *
     * @param payment The amount to add to amountPresent.
     */
    public void addPayment(Amount payment){
        this.amountPresent.increaseAmount(payment);

        System.out.println("Amount present in the cash register: " + this.amountPresent + " kr\n");
    }
}
