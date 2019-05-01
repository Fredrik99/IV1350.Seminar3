package se.kth.iv1350.saleProcess.model;

import se.kth.iv1350.saleProcess.dbhandler.ItemDTO;
import se.kth.iv1350.saleProcess.util.Amount;

/**
 * This class handles the sale operations.
 */
public class Sale
{
    private int[] quantities;
    private ItemDTO[] itemDTOS;
    private SaleInfo saleInfo;

    /**
     * Creates an instance.
     */
    public Sale(){
        this.quantities = new int[0];
        this.itemDTOS = new ItemDTO[0];
        this.saleInfo = new SaleInfo();
    }

    /**
     * Handles what itemDTOS the current sale are supposed to include.
     * @param includedItemDTO The <code>ItemDTO<code/> which are to be included to the sale.
     * @param quantity The quantity of the <code>ItemDTO<code/> to be included.
     */
    public void includeItems(ItemDTO includedItemDTO, int quantity) {

        if (checkIdentifier(includedItemDTO.getIdentifier()))
            increaseItemQuantity(includedItemDTO, quantity);
        else
            includeNewItem(includedItemDTO, quantity);

        this.saleInfo.updateSaleInfo(this.itemDTOS, this.quantities);
    }

    /**Returns the current sales sale information.
     *
     * @return A <>SaleInfo</> object type containing the sale information.
     */
    public SaleInfo getSaleInfo(){
        return this.saleInfo;
    }

    /**
     * Returns the sales total price in form of an <code>Amount<code/>.
     *
     * @return The <code>Amount<code/> that is returned.
     */
    public Amount getTotalPrice(){
        return this.saleInfo.getSaleItemInfo().getPriceCalculator().getSaleTotalPrice();
    }

    /**
     * Compares the received identifier with the identifiers in the variable itemDTOS
     *
     * @param identifier The received identifier.
     * @return Returns true or false depending on if
     *         the identifier matches on in the variable itemDTOS.
     */
    private boolean checkIdentifier(int identifier){

        for(ItemDTO itemDTO : this.itemDTOS)
            if(itemDTO.getIdentifier() == identifier)
                return true;

        return false;

    }

    /**
     * Increases the quantity of the already present <code>ItemDTO<code/> type in itemDTOS.
     *
     * @param includedItems The included <code>ItemDTO<code/>
     * @param quantity The quantity of the included <code>ItemDTO<code/>.
     */
    private void increaseItemQuantity(ItemDTO includedItems, int quantity){
        for (int i = 0; i < this.itemDTOS.length; i++)
            if (this.itemDTOS[i].getIdentifier() == includedItems.getIdentifier())
                this.quantities[i] = this.quantities[i] + quantity;
    }

    /**
     * This method is called if there is a new <code>ItemDTO<code/> type received.
     *
     * @param includedItems The new <code>ItemDTO<code/> type received.
     * @param quantity The quantity of the new <code>ItemDTO<code/> type to be included.
     */
    private void includeNewItem(ItemDTO includedItems, int quantity){
        ItemDTO[] itemDTOHolder = new ItemDTO[this.itemDTOS.length + 1];

        int[] quantityHolder = new int[this.quantities.length + 1];

        for (int i = 0; i < this.itemDTOS.length; i++) {
            itemDTOHolder[i] = this.itemDTOS[i];
            quantityHolder[i] = this.quantities[i];
        }

        itemDTOHolder[itemDTOHolder.length - 1] = includedItems;
        quantityHolder[quantityHolder.length - 1] = quantity;

        this.itemDTOS = itemDTOHolder;
        this.quantities = quantityHolder;
    }
}
