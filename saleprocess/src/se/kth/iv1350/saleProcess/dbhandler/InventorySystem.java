package se.kth.iv1350.saleProcess.dbhandler;

import se.kth.iv1350.saleProcess.model.SaleLog;
import se.kth.iv1350.saleProcess.util.Amount;

/**
 * This class represents the stores inventory system
 * where the inventorys information is beeing stored.
 */
public class InventorySystem {
    private final ItemDTO[] itemDTOList;
    private SaleLog saleLog;

    /**
     * Creates an instance and instanciates itemDTOList
     * which is an array of fake items.
     */
     InventorySystem(){

        ItemDTO guitar = new ItemDTO( "Guitar", 0, new Amount(7000),"This is an electric guitar");
        ItemDTO piano = new ItemDTO("Piano", 1, new Amount(10000),"This is a Grand Piano");
        ItemDTO setOfDrums = new ItemDTO("Set of drums", 2, new Amount(5000), "This is a set of drums containing 5 drums");
        ItemDTO bass = new ItemDTO("Bass", 3, new Amount(3000), "This is a five string bass");

        this.itemDTOList = new ItemDTO[]{guitar, piano, setOfDrums, bass};
    }

    /**
     * Returns the wanted items entered via the view class.
     * @param itemID The identification  number of the wanted item.
     * @return Returns the wanted items.
     */
    public ItemDTO getItemFromInventorySystem(int itemID){
        return this.itemDTOList[itemID];
    }

    /**
     * Sends sale information to the inventory for update.
     *
     * @param saleLog Sale information in form of <code>SaleLog<code/>.
     */
    public void updateInventorySystem (SaleLog saleLog){
        this.saleLog = saleLog;

        System.out.println("***Inventory has ben updated!***\n\n");
    }
}
