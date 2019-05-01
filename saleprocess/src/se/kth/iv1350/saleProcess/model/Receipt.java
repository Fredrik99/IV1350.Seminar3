package se.kth.iv1350.saleProcess.model;

/**
 * This class represents the receipt
 * the customer of the finalized sale receives.
 */
public class Receipt {
    private SaleLog saleLog;

    /**
     * Creates an instance.
     *
     * @param saleLog A <code>SaleLog<code/> object which contains
     *                all the sale information needed to print
     *                the receipt.
     */
    Receipt(SaleLog saleLog){

        this.saleLog = saleLog;
    }

    /**
     * Represents the printed format of the receipt.
     *
     * @return The receipt in form of a <code>String<code/>.
     */
    public String toString(){

        StringBuilder builder = new StringBuilder();

        builder.append("****************************************\n");
        builder.append("               RECEIPT\n\n");
        builder.append(this.saleLog.getSaleInfo().getTimOfSale() + "\n\n");
        builder.append(this.saleLog.getSaleInfo().getStoreInfo());
        builder.append("Included items: \n\n");

         for(int i = 0; i < this.saleLog.getSaleInfo().getSaleItemInfo().getNumberOfItemTypes(); i++){
             builder.append(this.saleLog.getSaleInfo().getSaleItemInfo().getItemNames(i) + ", ");
             builder.append("Price: " + this.saleLog.getSaleInfo().getSaleItemInfo().getPriceCalculator().getItemPrice(i) + " kr, ");
             builder.append("Quantity: " + this.saleLog.getSaleInfo().getSaleItemInfo().getPriceCalculator().getQuantities(i) + "\n");
         }

         builder.append("\nTotal VAT-amount: " + this.saleLog.getSaleInfo().getSaleItemInfo().getPriceCalculator().getSaleTotalVAT() + " kr \n");
         builder.append("Total Price: " + this.saleLog.getSaleInfo().getSaleItemInfo().getPriceCalculator().getSaleTotalPrice() + " kr \n\n");
         builder.append("Amount paid: " + this.saleLog.getPayment().getPaidAmount() + " kr \n");
         builder.append("Change: " + this.saleLog.getPayment().getChange() + " kr\n\n");
         builder.append("****************************************\n");

        return builder.toString();
    }
}
