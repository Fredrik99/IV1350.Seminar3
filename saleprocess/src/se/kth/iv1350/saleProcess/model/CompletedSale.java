package se.kth.iv1350.saleProcess.model;

import se.kth.iv1350.saleProcess.dbhandler.SystemCreator;

/**
 * This class finalizes the current sale.
 */
 class CompletedSale {
    private SaleLog saleLog;
    private Receipt receipt;

    /**
     * Creates an instance.
     *
     * @param payment All the transaction payment of the sale.
     * @param currentSale All the information in the current sale.
     */
     CompletedSale(Payment payment, Sale currentSale, SystemCreator systemCreator){

        this.saleLog = new SaleLog(currentSale.getSaleInfo(), payment);
        this.receipt = new Receipt(this.saleLog);

        updateExternalSystems(systemCreator);
        printReceipt(systemCreator);
    }

    /**
     * Updates external systems by sending the appropriate sale information.
     *
     * @param systemCreator Object type <code>SystemCreator<code/> which contains
     *                     different system classes.
     */
    private void updateExternalSystems(SystemCreator systemCreator){
        systemCreator.getExternalAccounting().updateExternalAccounting(this.saleLog);
        systemCreator.getInventorySystem().updateInventorySystem(this.saleLog);
    }

    /**
     * This method prints the receipt for the customer.
     *
     * @param systemCreator An object of type <code>SystemCreator<code/>
     *                      which contains an object ofd type <code>Printer<code/>.
     */
    private void printReceipt(SystemCreator systemCreator){
        systemCreator.getPrinter().printReceipt(this.receipt);
    }
}
