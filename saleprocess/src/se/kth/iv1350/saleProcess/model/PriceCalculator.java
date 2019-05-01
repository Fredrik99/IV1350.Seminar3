package se.kth.iv1350.saleProcess.model;

import se.kth.iv1350.saleProcess.util.Amount;

/**
 * This class handles all the price calculations of the current sale.
 */
public class PriceCalculator {

    private int[] itemQuantities;
    private Amount[] itemPrices;
    private Amount[] VATRates;
    private Amount[] itemsTotalPrice;
    private Amount[] totalItemVAT;
    private Amount saleTotalVAT;
    private Amount saleTotalPrice;


    PriceCalculator(Item[] items){

        setItemQuantities(items);
        setItemPrices(items);
        setTotalItemVAT(items);
        calculateItemsTotalPrice();
        calculateItemVAT();
        calculateSaleTotalVAT();
        calculateSaleTotalPrice();
    }

    /**
     * Sets the itemQuantities of the <code>Item</> of the sale.
     *
     * @param items the <code>Item</> from which to get the itemQuantities.
     */
    private void setItemQuantities(Item[] items){
        int[] quantities = new int[items.length];

        for(int i = 0; i < quantities.length; i ++)
            quantities[i] = items[i].getQuantity();

        this.itemQuantities = quantities;
    }

    /**
     * Returns the <code>Item<code/> itemQuantities of the sale.
     *
     * @param index The index where the <code>Item<code/> is stored.
     * @return Teh returned quantity.
     */
     int getQuantities(int index){

        return this.itemQuantities[index];
    }

    /**
     * Calculates the item prices of each individual <code><Item/code>.
     *
     * @param items The <code>Item<code/> from which to calculate the total.
     */
    private void setItemPrices(Item[] items){
        Amount[] individualItemsPrice = new Amount[items.length];

        for (int i = 0; i < individualItemsPrice.length; i++)
            individualItemsPrice[i] = new Amount();

        for(int i = 0; i < individualItemsPrice.length; i++)
            individualItemsPrice[i].setAmount(items[i].getPrice());

        this.itemPrices = individualItemsPrice;
    }

    /**
     * Returns the <code>Item<code/> price of the sent index.
     *
     * @param index The index of the wanted <code>Item<code/> price
     * @return The <code>Amount<code/> of the <code>Item<code/> price.
     */
    Amount getItemPrice(int index){
        return this.itemPrices[index];
    }

    /**
     * Calculates and sets the VATRates-itemPrices for each individual <code>Item<code/> included in the sale.
     *
     * @param items The multiple <code>Item<code/> from which you get the VATRates-rate of each <code>Item<code/>.
     */
    private void setTotalItemVAT(Item[] items){
        Amount[] individualItemsVAT = new Amount[this.itemPrices.length];

        for (int i = 0; i < individualItemsVAT.length; i++)
            individualItemsVAT[i] = new Amount();

        for (int i = 0; i < individualItemsVAT.length; i++)
            individualItemsVAT[i].setAmount(items[i].getVAT().getItemVAT());

        this.VATRates = individualItemsVAT;
    }

    /**
     * Returns the <code>Item<code/> VATRates at the sent index.
     *
     * @param index The sent index.
     * @return The VATRates in form of <code>Amount<code/>.
     */
     Amount getVATRates(int index){

        Amount amount = new Amount(100);
        amount.multiplyAmount(this.VATRates[index]);

        return amount;
    }

    /**
     * Calculates the running total of the sale.
     */
    private void calculateItemsTotalPrice(){

        Amount[] itemTotal = new Amount[this.itemQuantities.length];

        for(int i = 0; i < itemTotal.length; i++)
            itemTotal[i] = new Amount();

        for (int i = 0; i < itemTotal.length; i++)
            for(int j = 0; j < this.itemQuantities[i]; j++)
                itemTotal[i].increaseAmount(itemPrices[i]);

            this.itemsTotalPrice = itemTotal;
    }

    /**
     * Returns each items of the sales total price.
     *
     * @return The items total price in form of a <code>Amount<code/>.
     */
     Amount getItemsTotalPrice(int index) {
        return itemsTotalPrice[index];
    }

    /**
     * Calculates the VAT-total of each indivdual item type.
     */
    private void calculateItemVAT(){

        Amount[] totalVAT = new  Amount[this.itemQuantities.length];

        for(int i = 0; i < totalVAT.length; i++)
            totalVAT[i] = new Amount();

        for (int i = 0; i < totalVAT.length; i++) {
            totalVAT[i].increaseAmount(this.VATRates[i]);
            totalVAT[i].multiplyAmount(this.itemsTotalPrice[i]);
        }

        this.totalItemVAT = totalVAT;
    }

    /**
     * Returns the VAT-total of each individual item type.
     *
     * @param index The index of the wanted item type.
     * @return The VAT-amount in form of <code>Amount<code/>.
     */
    Amount getTotalItemVAT(int index){
        return this.totalItemVAT[index];
    }

    /**
     * Calculates the total VAT-amount of the current sale
     */
    private void calculateSaleTotalVAT(){

        Amount totalVAT = new Amount();

        for(Amount amount: this.totalItemVAT)
            totalVAT.increaseAmount(amount);

        this.saleTotalVAT = totalVAT;
    }

    /**
     * Returns the calculated total VAT-amount of the current sale.
     * @return The total VAT-amount in form of a <code>Amount<code/> object.
     */
    Amount getSaleTotalVAT(){
        return this.saleTotalVAT;
    }

    /**
     * Calculates the total price amount of the current sale
     */
    private void calculateSaleTotalPrice(){

        this.saleTotalPrice = new Amount();
        Amount totalPrice = new Amount();

        for(int i = 0; i < itemsTotalPrice.length; i++)
            totalPrice.increaseAmount(this.itemsTotalPrice[i]);

        totalPrice.increaseAmount(this.saleTotalVAT);

        this.saleTotalPrice = totalPrice;
    }

    /**
     * Returns the total price of the current sale.
     * @return The total amount in form of a <code>Amount<code/> object.
     */
    Amount getSaleTotalPrice(){
        return this.saleTotalPrice;
    }

}
