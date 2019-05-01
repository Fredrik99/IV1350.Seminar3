package se.kth.iv1350.saleProcess.model;

import se.kth.iv1350.saleProcess.dbhandler.ItemDescriptionDTO;
import se.kth.iv1350.saleProcess.dbhandler.VATDTO;
import se.kth.iv1350.saleProcess.util.Amount;

/**
 * This class represents the stores items and
 * contains all the information needed for each item.
 */
public class Item {

    private String name;
    private int identifier;
    private int quantity;
    private Amount price;
    private ItemDescriptionDTO description;
    private VATDTO vatRate;


    /**
     * Creates an <code>Item<code/> instance.
     * @param name The name of the created item.
     * @param identifier Each items identifier.
     * @param price The price of an item of the chosen identifier.
     */
    public Item(String name, int identifier, Amount price, String description){
        this.name = name;
        this.identifier = identifier;
        this.price = price;
        this.description = new ItemDescriptionDTO(description);
        this.vatRate = new VATDTO(identifier);
    }

    /**
     * A toString method.
     */
    public String toString(){
      return description.getDescription();
    }

    /**
     * Sets the wanted item quantity.
     *
     * @param quantity The quantity of the wanted item.
     */
    void setQuantity(int quantity){
        this.quantity = quantity;
    }

    /**
     *
     * @return Returns the quantity of the included item.
     */
     int getQuantity(){
        return this.quantity;
    }

    /**
     * @return Returns the current items identifier.
     */
     int getIdentifier(){
        return this.identifier;
    }

    /**
     * Returns the price of the <code>Item<code/>
     * @return The return value in fomr of <code>Amount<code/>
     */
     Amount getPrice(){
        return this.price;
    }

    /**
     * Returns the VAT-rate of the current <code>Item<code/>.
     * @return The <code>VATDTO<code/> of the current <code>Item<code/>.
     */
     VATDTO getVAT(){
        return this.vatRate;
    }

    /**
     * Returns the name of the current object <code>Item<code/>.
     * @return The name which is returned.
     */
     String getName(){
        return this.name;
    }

    /**
     * Returns the the descritption of a <code>Item<code/>
     * @return The <code>String<code/> containing the description.
     */
     String getDescription(){
        return this.description.getDescription();
    }
}
