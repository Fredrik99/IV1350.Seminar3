package se.kth.iv1350.saleProcess.controller;

import se.kth.iv1350.saleProcess.dbhandler.ChangeDTO;
import se.kth.iv1350.saleProcess.dbhandler.InventorySystemException;
import se.kth.iv1350.saleProcess.model.SaleInfo;
import se.kth.iv1350.saleProcess.dbhandler.SystemCreator;
import se.kth.iv1350.saleProcess.model.Payment;
import se.kth.iv1350.saleProcess.model.Sale;
import se.kth.iv1350.saleProcess.dbhandler.SaleObserver;
import se.kth.iv1350.saleProcess.util.Amount;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the applications only controller class.
 * All calls to the model pass through here.
 */
public class Controller {
    private Sale currentSale;
    private SystemCreator systemCreator;
    private Payment payment;
    private List<SaleObserver> saleObservers = new ArrayList<>();

    /**
     * Creates a new instance and initiates the variable systemCreator.
     */
    public Controller(SystemCreator systemCreator){
        this.systemCreator = systemCreator;
    }

    /**
     * Creates an instance of the object type <code>Sale<code/>
     * and associates it with the variable name currentSale.
     */
    public void startNewSale() {
    this.currentSale = new Sale();
        System.out.println("\n\n    ***A new sale has started***\n\n");
    }

    /**
     * Handles the current sales item registrations.
     *
     * @param itemID The <code>ItemDTO<code/> identification number.
     * @param quantity The quantity of the <code>ItemDTO<code/>.
     * @return The current sale information in form of a <code>SaleInfo<code/>.
     * @throws <code>InvalidIdentifierException<code/> when user enters an invalid item ID or
     * <code>OperationFailedException<code/> when there is a database failure.
     */
    public SaleInfo enterItem(int itemID, int quantity) throws OperationFailedException, InvalidIdentifierException {

        try {
            currentSale.includeItems(this.systemCreator.getInventorySystem().getItemFromInventorySystem(itemID), quantity);
        }
        catch (InventorySystemException exception){
            System.out.println("LOG MESSAGE: " + exception.getMessage());
            throw new InvalidIdentifierException(itemID);
        }
        catch (RuntimeException exception){
            System.out.println("LOG MESSAGE: " + exception.getMessage());
            throw new OperationFailedException("Program could not get access to the database", exception);
        }

        return currentSale.getSaleInfo();
    }

    /**
     * Method used when all items of the sale has been registered.
     *
     * @return The total price including VAT of the sale in the form of a <code>String<code/>.
     */
    public String allItemsRegistered(){

        StringBuilder builder = new StringBuilder();

        builder.append("Total price(including VAT): ");
        builder.append(this.currentSale.getTotalPrice() + "\n");

        return builder.toString();
    }

    /**
     * This method handles the payment part of the sale. It calculates
     * the change, updates the amount in the cash register and then completes the sale.
     *
     * @param paidAmount The amount paid.
     * @return The amount of change the customer receives in form of a <>ChangeDTO</>.
     */
    public ChangeDTO pay(Amount paidAmount){

        this.systemCreator.getCashRegister().addPayment(this.currentSale.getTotalPrice());
        this.payment = new Payment(paidAmount, this.currentSale.getTotalPrice());
        this.payment.completeSale(this.currentSale, this.systemCreator);

        return this.payment.getChange();
    }

    /**
     * Adds an observer to the <code>List<code/> saleObservers that are
     * going to be notified when a payment has been made.
     *
     * @param observer is the observer that is going to be notified
     *                of change in the <code>Payment<code/> class.
     */
   public void addSaleObserver(SaleObserver observer){
       this.saleObservers.add(observer);
       this.systemCreator.getCashRegister().addSaleObservers(this.saleObservers);
   }
}
