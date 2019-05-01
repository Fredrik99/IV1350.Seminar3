package se.kth.iv1350.saleProcess.model;

import se.kth.iv1350.saleProcess.dbhandler.ItemDTO;
import se.kth.iv1350.saleProcess.dbhandler.StoreDTO;
import se.kth.iv1350.saleProcess.dbhandler.TimeOfSaleDTO;

/**
 * This class contains the sale information of the current sale.
 */
public class SaleInfo {

    private StoreDTO storeInfo;
    private TimeOfSaleDTO timOfSale;
    private SaleItemInfo saleItemInfo;

    /**
     * Creates an instance.
     */
     SaleInfo(){

        this.saleItemInfo = new SaleItemInfo();
        this.storeInfo = new StoreDTO();
        this.timOfSale = new TimeOfSaleDTO();
    }

    /**
     * This method updates the sale information of the current sale.
     *
     * @param itemDTOS The <code>ItemDTO<code/> objects included in the sale.
     */
     void updateSaleInfo(ItemDTO[] itemDTOS, int[] quantities){

        this.saleItemInfo.updateItemInfo(itemDTOS, quantities);
    }

    /**
     * Returns the time of the finalized sale.
     *
     * @return An <code>TimeOfSaleDTO<code/> object type
     *         containing the time of the sale.
     */
     TimeOfSaleDTO getTimOfSale(){
        return this.timOfSale;
    }

    /**
     * Returns the current store information.
     *
     * @return A <code>StoreDTO<code/> object containing
     *         the name and adress of the store.
     */
     StoreDTO getStoreInfo(){
        return this.storeInfo;
    }

    /**
     * Returns the current sales item information.
     *
     * @return A <code>SaleItemInfo<code/> object type containing
     *         information about the sales items.
     */
     SaleItemInfo getSaleItemInfo(){
        return this.saleItemInfo;
    }

    /**
     * Returns a <code>String<code/> of the information of the current sale.
     *
     * @return The returned string of information.
     */
    public String toString(){
        StringBuilder builder = new StringBuilder();

        builder.append("Sale information:\n\n");

        for(int i = 0; i < this.saleItemInfo.getNumberOfItemTypes(); i++) {
            builder.append("ItemDTO: " + this.saleItemInfo.getItemNames(i) + ", ");
            builder.append("Price: " + this.saleItemInfo.getPriceCalculator().getItemPrice(i) + " kr, ");
            builder.append("Quantity: " + this.saleItemInfo.getPriceCalculator().getQuantities(i) + ", ");
            builder.append("VAT-rate: " + this.saleItemInfo.getPriceCalculator().getVATRates(i) + " %, ");
            builder.append("ItemDTO price total: " + this.saleItemInfo.getPriceCalculator().getItemsTotalPrice(i) + " kr, ");
            builder.append("VAT-amount: " + this.saleItemInfo.getPriceCalculator().getTotalItemVAT(i) + " kr, ");
            builder.append("Description: " + this.saleItemInfo.getDescriptions(i) + "\n\n");
        }

        builder.append("Running total(Including VAT): " + this.saleItemInfo.getPriceCalculator().getSaleTotalPrice() + " kr\n\n");

        return builder.toString();
    }

}