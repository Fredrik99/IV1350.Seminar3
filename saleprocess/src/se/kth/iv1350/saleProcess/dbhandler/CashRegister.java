package se.kth.iv1350.saleProcess.dbhandler;

import se.kth.iv1350.saleProcess.util.Amount;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the stores cash register where
 * the sales transactions are taking place and money is being handled.
 */
public class CashRegister {
    private Amount amountPresent;
    private List<SaleObserver> saleObservers = new ArrayList<>();

    /**
     * Creates an instance.
     */
    CashRegister(){
        this.amountPresent = new Amount(100000);
        System.out.println("\n\n  Amount present in the cash register: " + this.amountPresent + " kr\n");
    }

    /**
     * Adds the payment from the customer into the register.
     *
     * @param payment The amount to add to amountPresent and send to the <code>notifyObserver<code/> method.
     */
    public void addPayment(Amount payment){
        this.amountPresent.increaseAmount(payment);
        System.out.println("Amount present in the cash register: " + this.amountPresent + " kr\n");
        notifyObservers(payment);
    }

    /**
     * Adds observers that are observing the <code>CashRegister<code/> class.
     *
     * @param observers is the observer that are being added to the list of sale observers.
     */
    public void addSaleObservers(List<SaleObserver> observers) {
        this.saleObservers.addAll(observers);
    }

    /**
     * Notifies observer classes that a new amount has been paid.
     */
    private void notifyObservers(Amount payment){
        for (SaleObserver observer: this.saleObservers)
            observer.newAmountPaid(payment);
    }

    /**
     * Returns the amount present in the CashRegister
     * @return is the amount present in the CashRegister.
     */
     Amount getAmountPresent() {
        return amountPresent;
    }
}
