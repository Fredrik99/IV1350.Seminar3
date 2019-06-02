package se.kth.iv1350.saleProcess.util;

/**
 * This class handles calculations of different
 * data and then stores the data in the classobject.
 */
public final class Amount {
    private double amount;

    /**
     * Creates an instance.
     */
    public Amount(){
        this.amount = 0;
    }

    /**
     * Creates an instance with initial value.
     * @param amount The amount of the initial value.
     */
    public Amount(double amount){
        this.amount = amount;
    }

    /**
     * Increases the amount of <code>Amount<code/> object type.
     * @param amount The amount to be increased.
     */
    public void increaseAmount(Amount amount) {
        this.amount = amount.getAmount() + this.amount;
    }

    /**
     * Sets the amount of <code>Amount<code/>
     * @param amount The amount that is set.
     */
    public void setAmount(Amount amount) {
        this.amount = amount.getAmount();
    }

    /**
     * Multiplies with a an <code>Amount<code/>.
     * @param amount The amount to multiply the object with.
     */
    public void multiplyAmount(Amount amount){
        this.amount = this.amount*amount.getAmount();
    }

    /**
     * Subtracts two <code>Amount<code/> types.
     * @param amountNum The amount in the numerator.
     * @param amountDen The amount in the denominator.
     */
    public void subtractAmount (Amount amountNum, Amount amountDen){
        this.amount = amountNum.getAmount() - amountDen.getAmount();
    }

    /**
     * Returns the amount of the object.
     * @return Returns the amouint in form of <code>double<code/>.
     */
    public double getAmount(){
        return this.amount;
    }

    /**
     * Turns <code>Amount<code/> into a string with two decimal points.
     * @return A return value as a <code>String<code/>.
     */
    public String toString(){
        return Double.toString(this.amount);
    }

}
