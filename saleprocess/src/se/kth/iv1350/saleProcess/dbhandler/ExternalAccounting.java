package se.kth.iv1350.saleProcess.dbhandler;

import se.kth.iv1350.saleProcess.model.SaleLog;

/**
 * This class represents the store of
 * the current sales external accounting division.
 */
public class ExternalAccounting {
    private SaleLog saleLog;

    /**
     * Creates an instance.
     */
    public ExternalAccounting(){

    }

    /**
     * Sends sale information to external accounting for update.
     *
     * @param saleLog Sale information in form of <code>SaleLog<code/>.
     */
    public void updateExternalAccounting(SaleLog saleLog){
        this.saleLog = saleLog;

        System.out.println("***External Accounting has been updated!***\n");
    }
}
