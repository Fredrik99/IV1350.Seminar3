package se.kth.iv1350.saleProcess.model;

/**
 * This class i called upon when a discount has been requested by the user.
 */
public class Discount {
    private DiscountStrategy discountStrategy;

    /**
     * Creates an instance.
     */
    public Discount() {
    }

    /**
     * This method calculates the discount a customer is eligible for based
     * upon items bought total cost for the entire sale and customer id.
     *
     * @param customerID is the identification number the discount is based upon.
     * @param saleInfo   is the information about items and price of the current sale.å
     * @return the current sale information with discounted price.
     * @throws DiscountException when a customer is not eligible for a discount.
     */
    public void discountRequest(int customerID, SaleInfo saleInfo) throws DiscountException {

        if(discountValidation(customerID) == null)
            throw new DiscountException("The current customer identification is not eligible for a discount");

        discountStrategy = discountValidation(customerID);

        discountStrategy.calculateDiscount(saleInfo);
    }

    /**
     * THis method checks the customer identification to decide which discount the customer are eligible for.
     *
     * @param customerID is the customers identification.
     * @return is the appropriate discount strategy the customer is eligible for and
     * returns null if the customer is not eligible for a discount.
     */
    private DiscountStrategy discountValidation(int customerID){

        if(customerID < 10 && customerID >= 0)
            return new ConcreteDiscountA();
        else if (customerID >=10 && customerID < 20)
            return new ConcreteDiscountB();
        else if (customerID >= 20 && customerID < 30)
            return new ConcreteDiscountC();
        else
            return null;
    }

}
