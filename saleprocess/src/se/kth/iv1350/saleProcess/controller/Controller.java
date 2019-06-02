package se.kth.iv1350.saleProcess.controller;

import se.kth.iv1350.saleProcess.dbhandler.*;
import se.kth.iv1350.saleProcess.model.*;
import se.kth.iv1350.saleProcess.util.Amount;
import se.kth.iv1350.saleProcess.util.ExceptionLogger;

import java.io.PrintWriter;
import java.io.StringWriter;
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
    private Discount discount;

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
            throw new InvalidIdentifierException(itemID);
        }
        catch (DataBaseFailureException exception){
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            exception.printStackTrace(pw);
            ExceptionLogger.getInstance().log(sw.toString());
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
     * This method is called for when a customer signals that they are eligible for a discount.
     *
     * @param customerID is the identification number of the customer in question.
     * @return is the new price total after discount.
     */
    public String discountRequest(int customerID) {

        this.discount = new Discount();

       try {
           this.discount.discountRequest(customerID, this.getCurrentSale().getSaleInfo());
       }
       catch(DiscountException exception){
           return exception.getMessage() + "\n";
       }

        String discountPrice = "Discounted price: " + this.currentSale.getSaleInfo().getSaleItemInfo().getPriceCalculator().getSaleTotalPrice() + "\n";

        return discountPrice;
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

    /**
     * Returns the current sale attribute
     * @return is the Sale object being returned.
     */
     Sale getCurrentSale() {
        return currentSale;
    }

    /**
     * Returns the payment attribute.
     * @return is the returned payment object.
     */
     Payment getPayment() {
        return payment;
    }
}
