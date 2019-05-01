package se.kth.iv1350.saleProcess.dbhandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This is a class which records the time
 * of the current sale.
 */
public final class TimeOfSaleDTO {
    private String timeOfSale;

    /**
     * Creates an instance.
     */
    public TimeOfSaleDTO(){

        setTimeOfSale();
    }

    /**
     * Sets the time of the current sale.
     */
    private void setTimeOfSale(){

        StringBuilder builder = new StringBuilder();
        LocalDateTime timeOfSale = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        builder.append("Time of sale: ");
        builder.append(timeOfSale.format(formatter));

        this.timeOfSale = builder.toString();
    }

    /**
     * Returns the time of sale in form of a <code>String<code/>.
     *
     * @return The returned time of sale.
     */
    public String toString(){
        return this.timeOfSale;
    }
}
