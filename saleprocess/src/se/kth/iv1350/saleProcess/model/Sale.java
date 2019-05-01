package se.kth.iv1350.saleProcess.model;

import se.kth.iv1350.saleProcess.util.Amount;

/**
 * This class handles the sale operations.
 */
public class Sale
{
    private Item[] items;
    private SaleInfo saleInfo;

    /**
     * Creates an instance.
     */
    public Sale(){
        this.items = new Item[0];
        this.saleInfo = new SaleInfo();
    }

    /**
     * Handles what items the current sale are supposed to include.
     * @param includedItem The <code>Item<code/> which are to be included to the sale.
     * @param quantity The quantity of the <code>Item<code/> to be included.
     */
    public void includeItems(Item includedItem, int quantity) {

        if (checkIdentifier(includedItem.getIdentifier()))
            increaseItemQuantity(includedItem, quantity);
        else
            includeNewItem(includedItem, quantity);

        this.saleInfo.updateSaleInfo(this.items);
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
     * Compares the received identifier with the identifiers in the variable items
     *
     * @param identifier The received identifier.
     * @return Returns true or false depending on if
     *         the identifier matches on in the variable items.
     */
    private boolean checkIdentifier(int identifier){

        for(Item item: this.items)
            if(item.getIdentifier() == identifier)
                return true;

        return false;

    }

    /**
     * Increases the quantity of the already present <code>Item<code/> type in items.
     *
     * @param includedItems The included <code>Item<code/>
     * @param quantity The quantity of the included <code>Item<code/>.
     */
    private void increaseItemQuantity(Item includedItems, int quantity){
        for (int i = 0; i < this.items.length; i++)
            if (this.items[i].getIdentifier() == includedItems.getIdentifier())
                this.items[i].setQuantity(this.items[i].getQuantity() + quantity);
    }

    /**
     * This method is called if there is a new <code>Item<code/> type received.
     *
     * @param includedItems The new <code>Item<code/> type received.
     * @param quantity The quantity of the new <code>Item<code/> type to be included.
     */
    private void includeNewItem(Item includedItems, int quantity){
        Item[] itemHolder = new Item[this.items.length + 1];

        for (int i = 0; i < this.items.length; i++)
            itemHolder[i] = this.items[i];

        itemHolder[itemHolder.length - 1] = includedItems;
        itemHolder[itemHolder.length - 1].setQuantity(quantity);

        this.items = itemHolder;
    }
}
