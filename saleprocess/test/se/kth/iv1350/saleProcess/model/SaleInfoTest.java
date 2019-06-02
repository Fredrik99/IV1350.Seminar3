package se.kth.iv1350.saleProcess.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import se.kth.iv1350.saleProcess.dbhandler.ItemDTO;
import se.kth.iv1350.saleProcess.util.Amount;

import static org.junit.Assert.*;

public class SaleInfoTest {
    private SaleInfo saleInfo;
    private ItemDTO[] itemDTOS;
    private int[] quantities;

    @Before
    public void setUp(){

        saleInfo = new SaleInfo();
        itemDTOS = new ItemDTO[]{new ItemDTO( "Potato", 0, new Amount(5),"This is potato"),
                                 new ItemDTO( "Carrot", 1, new Amount(10),"This is a carrot"),
                                 new ItemDTO( "Onion", 2, new Amount(20),"This is an onion")};

        quantities = new int[]{1, 1, 1};
        saleInfo.updateSaleInfo(itemDTOS, quantities);
    }

    @After
    public void tearDown(){
        saleInfo = null;
        itemDTOS = null;
        quantities = null;
    }

    @Test
    public void updateSaleInfoNameTest() {
        assertEquals("Name does not equal Potato", "Potato", saleInfo.getSaleItemInfo().getItemNames(0));
        assertEquals("Name does not equal Carrot", "Carrot", saleInfo.getSaleItemInfo().getItemNames(1));
        assertEquals("Name does not equal Onion", "Onion", saleInfo.getSaleItemInfo().getItemNames(2));
    }

    @Test
    public void updateSaleInfoDescriptionTest() {
        assertEquals("Description does not equal the assigned one", "This is potato", saleInfo.getSaleItemInfo().getDescriptions(0));
        assertEquals("Description does not equal the assigned one", "This is a carrot", saleInfo.getSaleItemInfo().getDescriptions(1));
        assertEquals("Description does not equal the assigned one", "This is an onion", saleInfo.getSaleItemInfo().getDescriptions(2));
    }

    @Test
    public void toStringTest() {

        itemDTOS[0] = new ItemDTO( "Guitar", 0, new Amount(7000),"This is an electric guitar");
        itemDTOS[1] = new ItemDTO("Piano", 1, new Amount(10000),"This is a Grand Piano");
        itemDTOS[2] = new ItemDTO("Set of drums", 2, new Amount(5000), "This is a set of drums containing 5 drums");

        saleInfo.updateSaleInfo(itemDTOS, quantities);

        assertEquals("The printout does not equal the expeted one", "Sale information:\n\n" +
                "Item: Guitar, Price: 7000.0 kr, Quantity: 1, VAT-rate: 25.0 %, Item price total: 7000.0 kr, VAT-amount: 1750.0 kr, Description: This is an electric guitar\n\n" +
                "Item: Piano, Price: 10000.0 kr, Quantity: 1, VAT-rate: 12.0 %, Item price total: 10000.0 kr, VAT-amount: 1200.0 kr, Description: This is a Grand Piano\n\n" +
                "Item: Set of drums, Price: 5000.0 kr, Quantity: 1, VAT-rate: 6.0 %, Item price total: 5000.0 kr, VAT-amount: 300.0 kr, Description: This is a set of drums containing 5 drums\n\n" +
                "Running total(Including VAT): 25250.0 kr\n\n", saleInfo.toString());
    }
}