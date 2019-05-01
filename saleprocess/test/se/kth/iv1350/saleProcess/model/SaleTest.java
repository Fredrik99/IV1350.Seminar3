package se.kth.iv1350.saleProcess.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import se.kth.iv1350.saleProcess.dbhandler.ItemDTO;
import se.kth.iv1350.saleProcess.util.Amount;

import static org.junit.Assert.*;

public class SaleTest {
    private Sale sale;

    @Before
    public void setUp(){
        this.sale = new Sale();
    }

    @After
    public void tearDown(){
        this.sale = null;
    }


    @Test
    public void includeItemsTestQuantity_NoItemsPrior() {

        ItemDTO[] newItemDTOS = {new ItemDTO("Boll", 0, new Amount(10), "Boll Description")};

        int quantity = 5;

        this.sale.includeItems(newItemDTOS[0],quantity);
        int result = this.sale.getSaleInfo().getSaleItemInfo().getPriceCalculator().getQuantities(0);

        assertEquals("The quantity does not equal 5", quantity, result);
    }

    @Test
    public void includeItemsTestQuantity_IncreaseQuantity() {

        ItemDTO[] newItemDTOS = {new ItemDTO("Boll", 0, new Amount(10), "Boll Description")};

        int quantity = 5;
        int secondQuantity = 1;
        int expResult = quantity + secondQuantity;

        this.sale.includeItems(newItemDTOS[0],quantity);
        this.sale.includeItems(newItemDTOS[0],secondQuantity);
        int result = this.sale.getSaleInfo().getSaleItemInfo().getPriceCalculator().getQuantities(0);

        assertEquals("The quantity does not equal 6", expResult, result);
    }

    @Test
    public void includeItemsTestQuantity_IncludeSecondItem() {

        ItemDTO[] newItemDTOS = {new ItemDTO("Boll", 0, new Amount(10), "Boll Description"),
                new ItemDTO("Sko", 1, new Amount(100), "Sko Description")};

        int firstQuantity = 5;
        int secondQuantity = 1;

        this.sale.includeItems(newItemDTOS[0], firstQuantity);
        this.sale.includeItems(newItemDTOS[1], secondQuantity);
        int firstResult = this.sale.getSaleInfo().getSaleItemInfo().getPriceCalculator().getQuantities(0);
        int secondResult = this.sale.getSaleInfo().getSaleItemInfo().getPriceCalculator().getQuantities(1);

        assertEquals("The first quantity does not equal 5", firstQuantity, firstResult);
        assertEquals("The second quantity does not equal 1", secondQuantity, secondResult);
    }

    @Test
    public void includeItemsTestQuantity_IncreaseQuantityWithSecondItem() {

        ItemDTO[] newItemDTOS = {new ItemDTO("Boll", 0, new Amount(10), "Boll Description"),
                new ItemDTO("Sko", 1, new Amount(100), "Sko Description")};

        int firstQuantity = 5;
        int secondQuantity = 1;
        int thirdQuantity = 2;
        int fourthQuantity = 3;
        int expResult1 = firstQuantity + thirdQuantity;
        int expResult2 = secondQuantity + fourthQuantity;

        this.sale.includeItems(newItemDTOS[0], firstQuantity);
        this.sale.includeItems(newItemDTOS[1], secondQuantity);
        this.sale.includeItems(newItemDTOS[0], thirdQuantity);
        this.sale.includeItems(newItemDTOS[1], fourthQuantity);
        int firstResult = this.sale.getSaleInfo().getSaleItemInfo().getPriceCalculator().getQuantities(0);
        int secondResult = this.sale.getSaleInfo().getSaleItemInfo().getPriceCalculator().getQuantities(1);

        assertEquals("The first quantity does not equal 7", expResult1, firstResult);
        assertEquals("The second quantity does not equal 4", expResult2, secondResult);
    }

    @Test
    public void includeItemsTestPrice_NoItemsPrior() {

        ItemDTO[] newItemDTOS = {new ItemDTO("Boll", 0, new Amount(10), "Boll Description")};

        int quantity = 5;

        this.sale.includeItems(newItemDTOS[0],quantity);
        Amount result = this.sale.getSaleInfo().getSaleItemInfo().getPriceCalculator().getItemsTotalPrice(0);

        assertEquals("The price does not equal 50", quantity*10, result.getAmount(),0.001);
    }

    @Test
    public void includeItemsTestPrice_IncreaseQuantity() {

        ItemDTO[] newItemDTOS = {new ItemDTO("Boll", 0, new Amount(10), "Boll Description")};

        int quantity = 5;
        int secondQuantity = 1;
        int expResult = (quantity + secondQuantity)*10;

        this.sale.includeItems(newItemDTOS[0],quantity);
        this.sale.includeItems(newItemDTOS[0],secondQuantity);
        Amount result = this.sale.getSaleInfo().getSaleItemInfo().getPriceCalculator().getItemsTotalPrice(0);

        assertEquals("The price does not equal 60", expResult, result.getAmount(),0.001);
    }

    @Test
    public void includeItemsTestPrice_IncludeSecondItem() {

        ItemDTO[] newItemDTOS = {new ItemDTO("Boll", 0, new Amount(10), "Boll Description"),
                new ItemDTO("Sko", 1, new Amount(100), "Sko Description")};

        int firstQuantity = 5;
        int secondQuantity = 2;

        this.sale.includeItems(newItemDTOS[0], firstQuantity);
        this.sale.includeItems(newItemDTOS[1], secondQuantity);
        Amount firstResult = this.sale.getSaleInfo().getSaleItemInfo().getPriceCalculator().getItemsTotalPrice(0);
        Amount secondResult = this.sale.getSaleInfo().getSaleItemInfo().getPriceCalculator().getItemsTotalPrice(1);

        assertEquals("The first price does not equal 50", firstQuantity*10, firstResult.getAmount(), 0.001);
        assertEquals("The second price does not equal 200", secondQuantity*100, secondResult.getAmount(), 0.001);
    }

    @Test
    public void includeItemsTestPrice_IncreaseQuantityWithSecondItem() {

        ItemDTO[] newItemDTOS = {new ItemDTO("Boll", 0, new Amount(10), "Boll Description"),
                new ItemDTO("Sko", 1, new Amount(100), "Sko Description")};

        int firstQuantity = 5;
        int secondQuantity = 1;
        int thirdQuantity = 2;
        int fourthQuantity = 3;
        int expResult1 = (firstQuantity + thirdQuantity)*10;
        int expResult2 = (secondQuantity + fourthQuantity)*100;

        this.sale.includeItems(newItemDTOS[0], firstQuantity);
        this.sale.includeItems(newItemDTOS[1], secondQuantity);
        this.sale.includeItems(newItemDTOS[0], thirdQuantity);
        this.sale.includeItems(newItemDTOS[1], fourthQuantity);
        Amount firstResult = this.sale.getSaleInfo().getSaleItemInfo().getPriceCalculator().getItemsTotalPrice(0);
        Amount secondResult = this.sale.getSaleInfo().getSaleItemInfo().getPriceCalculator().getItemsTotalPrice(1);

        assertEquals("The first price does not equal 7", expResult1, firstResult.getAmount(), 0.001);
        assertEquals("The second price does not equal 4", expResult2, secondResult.getAmount(), 0.001);
    }

}