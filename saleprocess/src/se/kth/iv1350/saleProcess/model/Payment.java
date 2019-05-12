package se.kth.iv1350.saleProcess.model;

import se.kth.iv1350.saleProcess.dbhandler.ChangeDTO;
import se.kth.iv1350.saleProcess.dbhandler.SaleObserver;
import se.kth.iv1350.saleProcess.dbhandler.SystemCreator;
import se.kth.iv1350.saleProcess.util.Amount;

import java.util.ArrayList;
import java.util.List;

/**
 * This class handles the payment part of the sale.
 */
public class Payment {
    private Amount paidAmount;
    private Amount totalPrice;
    private ChangeDTO change;
    private CompletedSale completedSale;


    /**
     * Creates an instance.
     */
    public Payment(Amount paidAmount, Amount totalPrice){
        this.paidAmount = paidAmount;
        this.totalPrice = totalPrice;
        calculateChange();
    }

    /**
     * Calculates what change to give to the customer.
     */
    private void calculateChange() {
        this.change = new ChangeDTO(this.paidAmount, this.totalPrice);
    }


    /**
     * This method finalizes the sale.
     *
     * @param currentSale The information of the current sale.
     * @param systemCreator An object type <code>SystemCreator<code/> which
     *                      contains the different systems that will receive
     *                      information about the completed sale.
     */
    public void completeSale(Sale currentSale, SystemCreator systemCreator){
        this.completedSale = new CompletedSale(this, currentSale, systemCreator);
    }

    /**
     * Returns the amount the customer of the current sale paid.
     *
     * @return The paind amount in form of a <code>Amount<code/> object.
     */
     Amount getPaidAmount(){
        return  this.paidAmount;
    }

    /**
     * Returns the change paid back to the customer of the current sale.
     *
     * @return The change of the sale in form of an <>Amount</> object.
     */
    public ChangeDTO getChange(){
        return this.change;
    }
}
