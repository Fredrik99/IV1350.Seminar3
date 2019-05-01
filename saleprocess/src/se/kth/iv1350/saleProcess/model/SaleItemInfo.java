package se.kth.iv1350.saleProcess.model;

/**
 * This class handles the item information of each sale.
 */
 class SaleItemInfo {

    private PriceCalculator priceCalculator;
    private String[] itemNames;
    private String[] itemDescriptions;

    /**
     * Creates an instance.
     */
     SaleItemInfo(){

        this.itemDescriptions = new String[0];
    }

    /**
     * Updates the information of the current sale.
     *
     * @param items Contains all the information about the included object types <>Item</>.
     */
     void updateItemInfo(Item[] items){

        setItemNames(items);
        setItemDescriptions(items);

        this.priceCalculator = new PriceCalculator(items);
    }

    /**
     * Sets the names of the <code>Item<code/> included in the sale.
     *
     * @param items The items from which to get the names to set.
     */
    private void setItemNames(Item[] items){
        String[] itemnames = new String[items.length];

        for(int i = 0; i < itemnames.length; i++)
            itemnames[i] = items[i].getName();

        this.itemNames = itemnames;
    }

    /**
     * Returns <code>Item<code/> names.
     *
     * @param index The index of the wanted <code>Item<code/> name.
     * @return The returned <code>Item<code/> name.
     */
     String getItemNames(int index){
        return this.itemNames[index];
    }

    /**
     * Returns how many individual <code>Item</> types there are in the current sale.
     *
     * @return The number of <code>Item</> types in form of a <code>Int</>
     */
     int getNumberOfItemTypes(){
        return this.itemNames.length;
    }

    /**
     * Set the <code>Item<code/> itemDescriptions
     *
     * @param items The <code>Item<code/> to get the descriptions from.
     */
    private void setItemDescriptions(Item[] items){
        String[] descriptions = new String[items.length];

        for (int i = 0; i < descriptions.length; i++)
            descriptions[i] = items[i].getDescription();

        this.itemDescriptions = descriptions;
    }

    /**
     * Returns the description of an <code>Item<code/>.
     *
     * @param index The index of the wanted <code>Item<code/>.
     * @return The description in form of a <code>String<code/>.
     */
     String getDescriptions(int index){
        return this.itemDescriptions[index];
    }

    /**
     * Returns an object of type <code>PriceCalculator<code/>
     *
     * @return The object priceCalculator.
     */
     PriceCalculator getPriceCalculator(){
        return this.priceCalculator;
    }
}
