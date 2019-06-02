package se.kth.iv1350.saleProcess.model;

/**
 * A strategy pattern interface for receiving the discounted price of a sale.
 * Classes that are intended to calculate discount at different rates implements this interface.
 */
public interface DiscountStrategy {

    /**
     * The method that is called upon when a discount is being requested.
     *
     * @return is the current sale information with discounted total price.
     */
     SaleInfo calculateDiscount( SaleInfo saleInfo);
}
