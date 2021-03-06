package se.kth.iv1350.saleProcess.model;

import se.kth.iv1350.saleProcess.dbhandler.ItemDTO;

/**
 * This class handles the item information of each sale.
 */
 public class SaleItemInfo {

    private PriceCalculator priceCalculator;
    private int[] itemIdentifiers;
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
     * @param itemDTOS Contains all the information about the included object types <>ItemDTO</>.
     */
     void updateItemInfo(ItemDTO[] itemDTOS, int[] quantities){

         setItemIdentifiers(itemDTOS);
         setItemNames(itemDTOS);
         setItemDescriptions(itemDTOS);

        this.priceCalculator = new PriceCalculator(itemDTOS, quantities);
    }

    /**
     * Sets the names of the <code>ItemDTO<code/> included in the sale.
     *
     * @param itemDTOS The itemDTOS from which to get the names to set.
     */
    private void setItemNames(ItemDTO[] itemDTOS){
        String[] itemnames = new String[itemDTOS.length];

        for(int i = 0; i < itemnames.length; i++)
            itemnames[i] = itemDTOS[i].getName();

        this.itemNames = itemnames;
    }

    /**
     * Returns <code>ItemDTO<code/> names.
     *
     * @param index The index of the wanted <code>ItemDTO<code/> name.
     * @return The returned <code>ItemDTO<code/> name.
     */
     String getItemNames(int index){
        return this.itemNames[index];
    }

    /**
     * Returns how many individual <code>ItemDTO</> types there are in the current sale.
     *
     * @return The number of <code>ItemDTO</> types in form of a <code>Int</>
     */
    public int getNumberOfItemTypes(){
        return this.itemNames.length;
    }

    /**
     * Set the <code>ItemDTO<code/> itemDescriptions
     *
     * @param itemDTOS The <code>ItemDTO<code/> to get the descriptions from.
     */
    private void setItemDescriptions(ItemDTO[] itemDTOS){
        String[] descriptions = new String[itemDTOS.length];

        for (int i = 0; i < descriptions.length; i++)
            descriptions[i] = itemDTOS[i].getDescription();

        this.itemDescriptions = descriptions;
    }

    /**
     * Sets the attribute itemIdentifiers to hava correct identifier to correct element position.
     * @param itemDTOS contains the items identifiers.
     */
    private void setItemIdentifiers(ItemDTO[] itemDTOS) {
        int[] identifiers = new int[itemDTOS.length];

        for (int i = 0; i < identifiers.length; i++)
            identifiers[i] = itemDTOS[i].getIdentifier();

        this.itemIdentifiers = identifiers;
    }

    /**
     * Checks if the array itemIdentifier contains a certain identifier.
     *
     * @param identifier is the identifier that needs comparison.
     * @return either true or false depending on the sent identifier exzists in itemIdentifiers or not.
     */
     int checkItemIdentifiers(int identifier) {

        for(int i = 0; i < this.itemIdentifiers.length; i++)
            if (itemIdentifiers[i] == identifier)
                return i;

        return -1;
    }

    /**
     * Returns the description of an <code>ItemDTO<code/>.
     *
     * @param index The index of the wanted <code>ItemDTO<code/>.
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
     public PriceCalculator getPriceCalculator(){
        return this.priceCalculator;
    }
}
