package se.kth.iv1350.saleProcess.model;

import se.kth.iv1350.saleProcess.util.Amount;

/**
 * This class contains discount rate for a specific item identifier.
 */
public class ConcreteDiscountB  implements DiscountStrategy{
    private final Amount DISCOUNT_RATE = new Amount(0.9);
    private final int DISCOUNTED_ITEM_IDENTIFIER = 1;

    /**
     * Creates an instance.
     */
     ConcreteDiscountB() {
    }

    /**
     * This method calculates the discount for the current sale.
     * @param saleInfo is the information sent about the current sale.
     * @return is the update discounted price in form of a <>SaleInfo</> object.
     */
    @Override
    public SaleInfo calculateDiscount( SaleInfo saleInfo) {

        if (saleInfo.getSaleItemInfo().checkItemIdentifiers(this.DISCOUNTED_ITEM_IDENTIFIER) >= 0) {
            int index = saleInfo.getSaleItemInfo().checkItemIdentifiers(this.DISCOUNTED_ITEM_IDENTIFIER);
            saleInfo.getSaleItemInfo().getPriceCalculator().calculateDiscount(this.DISCOUNT_RATE, index);
        }

        return saleInfo;
    }
}
