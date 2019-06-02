package se.kth.iv1350.saleProcess.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import se.kth.iv1350.saleProcess.dbhandler.ItemDTO;
import se.kth.iv1350.saleProcess.util.Amount;

import static org.junit.Assert.*;

public class SaleItemInfoTest {
    private ItemDTO[] itemDTOS;
    private int[] quantities;
    private SaleItemInfo saleItemInfo;

    @Before
    public void setUp() {
        itemDTOS = new ItemDTO[]{new ItemDTO( "Car", 0, new Amount(100000),"This is a car"),
                new ItemDTO( "Bike", 1, new Amount(5000),"This is a bike"),
                new ItemDTO( "Boat", 2, new Amount(700000),"This is a boat")};

        quantities = new int[]{1, 1, 1};
        saleItemInfo = new SaleItemInfo();
    }

    @After
    public void tearDown() {
        itemDTOS = null;
        quantities = null;
        saleItemInfo = null;
    }

    @Test
    public void updateItemInfoNameTest() {
        saleItemInfo.updateItemInfo(itemDTOS,quantities);
        assertEquals("Name does not equal car", "Car", saleItemInfo.getItemNames(0));
        assertEquals("Name does not equal bike", "Bike", saleItemInfo.getItemNames(1));
        assertEquals("Name does not equal boat", "Boat", saleItemInfo.getItemNames(2));
    }

    @Test
    public void updateItemInfoDescriptionsTest() {
        saleItemInfo.updateItemInfo(itemDTOS,quantities);
        assertEquals("Description does not match", "This is a car", saleItemInfo.getDescriptions(0));
        assertEquals("Description does not match", "This is a bike", saleItemInfo.getDescriptions(1));
        assertEquals("Description does not match", "This is a boat", saleItemInfo.getDescriptions(2));
    }

    @Test
    public void updateItemInfoNumberOfItemTypesTest() {
        saleItemInfo.updateItemInfo(itemDTOS,quantities);
        assertEquals("Number of item types is not 3", quantities.length, saleItemInfo.getNumberOfItemTypes());
    }
}