package se.kth.iv1350.saleProcess.dbhandler;

import se.kth.iv1350.saleProcess.util.Amount;

/**
 * This class represents the stores items and
 * contains all the information needed for each item.
 */
public class ItemDTO {

    private String name;
    private int identifier;
    private int quantity;
    private Amount price;
    private ItemDescriptionDTO description;
    private VATDTO vatRate;


    /**
     * Creates an <code>ItemDTO<code/> instance.
     * @param name The name of the created item.
     * @param identifier Each items identifier.
     * @param price The price of an item of the chosen identifier.
     */
    public ItemDTO(String name, int identifier, Amount price, String description){
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
   public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    /**
     *
     * @return Returns the quantity of the included item.
     */
    public int getQuantity(){
        return this.quantity;
    }

    /**
     * @return Returns the current items identifier.
     */
   public int getIdentifier(){
        return this.identifier;
    }

    /**
     * Returns the price of the <code>ItemDTO<code/>
     * @return The return value in fomr of <code>Amount<code/>
     */
    public Amount getPrice(){
        return this.price;
    }

    /**
     * Returns the VAT-rate of the current <code>ItemDTO<code/>.
     * @return The <code>VATDTO<code/> of the current <code>ItemDTO<code/>.
     */
    public VATDTO getVAT(){
        return this.vatRate;
    }

    /**
     * Returns the name of the current object <code>ItemDTO<code/>.
     * @return The name which is returned.
     */
    public String getName(){
        return this.name;
    }

    /**
     * Returns the the descritption of a <code>ItemDTO<code/>
     * @return The <code>String<code/> containing the description.
     */
   public String getDescription(){
        return this.description.getDescription();
    }
}
