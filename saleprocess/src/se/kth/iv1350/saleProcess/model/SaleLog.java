package se.kth.iv1350.saleProcess.model;

/**
 * This class logs all the information of the finalized sale.
 */
public class SaleLog {
    private SaleInfo saleInfo;
    private Payment payment;

    /**
     * Creates an instance.
     *
     * @param saleInfo Contains the sale information of the finalized sale.
     * @param payment Contains the payment information of the finalized sale.
     */
     SaleLog(SaleInfo saleInfo, Payment payment){
        this.saleInfo = saleInfo;
        this.payment = payment;
    }

    /**
     * Returns the sale information of the finalized sale.
     * @return A <code>SaleInfo<code/> object type as return value.
     */
     SaleInfo getSaleInfo(){
        return this.saleInfo;
    }

    /**
     * Returns the payment information of the finalized sale.
     * @return The payment information in form of a <code>Payment<code/> object.
     */
     Payment getPayment(){
        return this.payment;
    }
}
