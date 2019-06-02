package se.kth.iv1350.saleProcess.model;

import se.kth.iv1350.saleProcess.util.Amount;

/**
 * This class contains discount rate for a specific item identifier.
 */
public class ConcreteDiscountC implements DiscountStrategy{
    private final Amount DISCOUNT_RATE = new Amount(0.85);
    private final int DISCOUNTED_ITEM_IDENTIFIER = 2;

    /**
     * A constructor which instantiates an object.
     */
     ConcreteDiscountC() {
    }

    /**
     *This method calculates the discounted price depending on if a specific item is in the current sale.
     * @param saleInfo is the information concerning the current sale,
     * @return is the update discounted price in form of a <code>SaleInfo<code/>
     */
    @Override
    public SaleInfo calculateDiscount(SaleInfo saleInfo) {

        if (saleInfo.getSaleItemInfo().checkItemIdentifiers(this.DISCOUNTED_ITEM_IDENTIFIER) >= 0) {
            int index = saleInfo.getSaleItemInfo().checkItemIdentifiers(this.DISCOUNTED_ITEM_IDENTIFIER);
            saleInfo.getSaleItemInfo().getPriceCalculator().calculateDiscount(this.DISCOUNT_RATE, index);
        }

        return saleInfo;
    }
}
