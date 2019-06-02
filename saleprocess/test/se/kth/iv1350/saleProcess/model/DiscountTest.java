package se.kth.iv1350.saleProcess.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import se.kth.iv1350.saleProcess.dbhandler.ItemDTO;
import se.kth.iv1350.saleProcess.util.Amount;

import static org.junit.Assert.*;

public class DiscountTest {
    private Discount discount;
    private DiscountException discountException;
    private SaleInfo saleInfo;
    private ItemDTO[] itemDTOS;
    private int[] quantities;

    @Before
    public void setUp(){
        this.discount = new Discount();
        this.discountException = new DiscountException("The current customer identification is not eligible for a discount");
        this.saleInfo = new SaleInfo();
        this.itemDTOS = new ItemDTO[]{new ItemDTO( "Game", 0, new Amount(500),"This is game"),
                new ItemDTO( "Controller", 1, new Amount(200),"This is a controller"),
                new ItemDTO( "Computer", 2, new Amount(5000),"This is a computer")};

        this.quantities = new int[]{1, 1, 1};
        this.saleInfo.updateSaleInfo(itemDTOS, quantities);
    }

    @After
    public void tearDown() {
       this.discount = null;
        this.discountException = null;
        this.saleInfo = null;
        this.itemDTOS = null;
        this.quantities = null;
    }

    @Test
    public void discountRequestExceptionTest() {

        int customerID = 100;

        try{
            this.discount.discountRequest(customerID, this.saleInfo);
            fail("This item identification is eligible for a discount!");
        }
        catch (DiscountException exception){
            assertEquals("The objects don't have the same message!",this.discountException.getMessage(), exception.getMessage());
        }
    }

    @Test
    public void discountRequestTestConcreteDiscountA() {

        int customerID = 1;

        try {
            this.discount.discountRequest(customerID, saleInfo);
        }
        catch (DiscountException exception){
            fail("This ID is not eligible for a discount");
        }

        assertEquals("The expected discount is not correct!",475.0, saleInfo.getSaleItemInfo().getPriceCalculator().getItemsTotalPrice(0).getAmount(),0.001);
    }

    @Test
    public void discountRequestTestConcreteDiscountB() {

        int customerID = 11;

        try {
            this.discount.discountRequest(customerID, saleInfo);
        }
        catch (DiscountException exception){
            fail("This ID is not eligible for a discount");
        }

        assertEquals("The expected discount is not correct!",180.0, saleInfo.getSaleItemInfo().getPriceCalculator().getItemsTotalPrice(1).getAmount(),0.001);
    }

    @Test
    public void discountRequestTestConcreteDiscountC() {

        int customerID = 25;

        try {
            this.discount.discountRequest(customerID, saleInfo);
        }
        catch (DiscountException exception){
            fail("This ID is not eligible for a discount");
        }

        assertEquals("The expected discount is not correct!",4250.0, saleInfo.getSaleItemInfo().getPriceCalculator().getItemsTotalPrice(2).getAmount(),0.001);
    }

}