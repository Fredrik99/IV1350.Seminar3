package se.kth.iv1350.saleProcess.dbhandler;

/**
 * A class that includes information about
 * the store where the sale of the application is taking place.
 */

public final class StoreDTO {
    private String storeName;
    private String storeAddress;

    /**
     *Creates an instance with a fake address and name.
     */
    public StoreDTO(){
        this.storeName = "Epic Store";
        this.storeAddress = "At the end of the rainbow";
    }

    /**
     * Returns the stores name and address in the form of a <code>String<code/>.
     * @return The returned <code>String<code/>.
     */
    public String toString(){
        StringBuilder builder = new StringBuilder();

        builder.append("Store name: " + this.storeName + "\n\n");
        builder.append("Store address: " + this.storeAddress + "\n\n");

        return builder.toString();
    }
}
