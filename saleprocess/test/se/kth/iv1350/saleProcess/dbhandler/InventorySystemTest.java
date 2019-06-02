package se.kth.iv1350.saleProcess.dbhandler;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import se.kth.iv1350.saleProcess.model.Payment;
import se.kth.iv1350.saleProcess.model.SaleInfo;
import se.kth.iv1350.saleProcess.model.SaleLog;
import se.kth.iv1350.saleProcess.util.Amount;

import static org.junit.Assert.*;

public class InventorySystemTest {
    private InventorySystem inventorySystem;
    private Exception inventorySystemException;
    private RuntimeException runtimeException;
    private ItemDTO item;
    private SaleInfo saleInfo;
    private ItemDTO[] itemDTOS;
    private int[] quantities;
    private SaleLog saleLog;

    @Before
    public void setUp() {
        this.inventorySystem = new InventorySystem();
        this.inventorySystemException = new InventorySystemException("User entered an item identifier that does not exist in inventory");
        this.runtimeException = new RuntimeException("Database failure");
        this.saleInfo = new SaleInfo();
        this.itemDTOS = new ItemDTO[]{new ItemDTO( "Game", 0, new Amount(500),"This is game"),
                new ItemDTO( "Controller", 1, new Amount(200),"This is a controller"),
                new ItemDTO( "Computer", 2, new Amount(5000),"This is a computer")};

        this.quantities = new int[]{1, 1, 1};
        this.saleInfo.updateSaleInfo(itemDTOS, quantities);
        this.saleLog = new SaleLog(saleInfo, new Payment(new Amount(10000), new Amount(10000)));
    }

    @After
    public void tearDown() {
        this.inventorySystem = null;
        this.inventorySystemException = null;
        this.runtimeException = null;
        this.item = null;
        this.saleInfo = null;
        this.itemDTOS = null;
        this.quantities = null;
        this.saleLog = null;
    }

    @Test
    public void getItemFromInventorySystemExceptionTest() {

        int itemID = 345;

            try{
                this.inventorySystem.getItemFromInventorySystem(itemID);
                fail("This item identification exists in inventory!");
            }
            catch (InventorySystemException exception){
                assertEquals("The objects don't have the same message!",this.inventorySystemException.getMessage(), exception.getMessage());
            }
    }

    @Test
    public void getItemFromInventoryDataBaseFailureTest() {

        int itemID = 100;

        try{
            this.inventorySystem.getItemFromInventorySystem(itemID);
            fail("No database failure");
        }
        catch (DataBaseFailureException | InventorySystemException exception){
            assertEquals("The objects don't have the same message!",this.runtimeException.getMessage(), exception.getMessage());
        }
    }

    @Test
    public void getItemFromInventorySystemTest1() {
        try{
           item = this.inventorySystem.getItemFromInventorySystem(1);
            assertEquals("Item identifier is not 1", 1, item.getIdentifier());
            assertEquals("Item price is not 10000", 10000.0, item.getPrice().getAmount(), 0.001);
            assertEquals("Item name is not Piano","Piano", item.getName());
        }
        catch (RuntimeException | InventorySystemException exception){
            fail(" getItemFromInventorySystem did not work as expected!");
        }
    }

    @Test
    public void getItemFromInventorySystemTest2() {
        try{
            item = this.inventorySystem.getItemFromInventorySystem(3);
            assertEquals("Item identifier is not 1", 3, item.getIdentifier());
            assertEquals("Item price is not 10000", 3000.0, item.getPrice().getAmount(), 0.001);
            assertEquals("Item name is not Piano","Bass", item.getName());
        }
        catch (RuntimeException | InventorySystemException exception){
            fail(" getItemFromInventorySystem did not work as expected!");
        }
    }

    @Test
    public void updateInventorySystemTestSaleLog() {

        this.inventorySystem.updateInventorySystem(saleLog);

        String expected = this.inventorySystem.getSaleLog().getPayment().getChange().toString();

        assertEquals("updateInventorySystem did not work as expected","0.0",expected);
    }

    @Test
    public void updateInventorySystemTestSaleLog2() {

        this.inventorySystem.updateInventorySystem(saleLog);

        String expected = this.inventorySystem.getSaleLog().getSaleInfo().getStoreInfo().toString();

        assertEquals("The stores name and address is not the expected!","Store name: " + "Epic Store" + "\n\n"
                + "Store address: " + "At the end of the rainbow" + "\n\n", expected);
    }

    @Test
    public void updateInventorySystemTestSaleLog3() {

        this.inventorySystem.updateInventorySystem(saleLog);

        int expected = this.inventorySystem.getSaleLog().getSaleInfo().getSaleItemInfo().getNumberOfItemTypes();

        assertEquals("updateInventorySystem did not work as expected",3,expected);
    }
}