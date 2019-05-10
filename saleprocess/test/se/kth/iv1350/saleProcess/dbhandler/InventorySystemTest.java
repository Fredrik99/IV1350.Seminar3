package se.kth.iv1350.saleProcess.dbhandler;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class InventorySystemTest {
    private InventorySystem inventorySystem;
    private Exception inventorySystemException;
    private RuntimeException runtimeException;

    @Before
    public void setUp() {
        this.inventorySystem = new InventorySystem();
        this.inventorySystemException = new InventorySystemException("User entered an item identifier that does not exist in inventory");
        this.runtimeException = new RuntimeException("Database failure");
    }

    @After
    public void tearDown() {
        this.inventorySystem = null;
        this.inventorySystemException = null;
        this.runtimeException = null;
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
    public void getItemFromInventoryRunTimeExceptionTest() {

        int itemID = 100;

        try{
            this.inventorySystem.getItemFromInventorySystem(itemID);
            fail("No database failure");
        }
        catch (RuntimeException | InventorySystemException exception){
            assertEquals("The objects don't have the same message!",this.runtimeException.getMessage(), exception.getMessage());
        }
    }

}