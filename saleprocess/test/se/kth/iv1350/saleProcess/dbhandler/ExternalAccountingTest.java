package se.kth.iv1350.saleProcess.dbhandler;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import se.kth.iv1350.saleProcess.model.Payment;
import se.kth.iv1350.saleProcess.model.SaleInfo;
import se.kth.iv1350.saleProcess.model.SaleLog;
import se.kth.iv1350.saleProcess.util.Amount;

import static org.junit.Assert.*;

public class ExternalAccountingTest {
    private ExternalAccounting externalAccounting;
    private SaleInfo saleInfo;
    private ItemDTO[] itemDTOS;
    private int[] quantities;
    private SaleLog saleLog;


    @Before
    public void setUp(){
        this.externalAccounting = new ExternalAccounting();
        this.saleInfo = new SaleInfo();
        this.itemDTOS = new ItemDTO[]{new ItemDTO( "Cat", 0, new Amount(500),"This is a cat"),
                new ItemDTO( "Dog", 1, new Amount(10000),"This is a dog"),
                new ItemDTO( "Horse", 2, new Amount(50000),"This is a horse")};

        this.quantities = new int[]{1, 1, 1};
        this.saleInfo.updateSaleInfo(itemDTOS, quantities);
        this.saleLog = new SaleLog(saleInfo, new Payment(new Amount(50000), new Amount(10000)));
        externalAccounting.updateExternalAccounting(saleLog);
    }

    @After
    public void tearDown() {
        this.externalAccounting = null;
        this.saleInfo = null;
        this.itemDTOS = null;
        this.quantities = null;
        this.saleLog = null;
    }

    @Test
    public void updateExternalAccounting() {
        int expected = this.externalAccounting.getSaleLog().getSaleInfo().getSaleItemInfo().getNumberOfItemTypes();
        assertEquals("updateExternalAccounting did not work as expected",3,expected);
    }

    @Test
    public void updateExternalAccounting1() {
        String expected = this.externalAccounting.getSaleLog().getSaleInfo().getStoreInfo().toString();
        assertEquals("The stores name and address is not the expected!","Store name: " + "Epic Store" + "\n\n"
                + "Store address: " + "At the end of the rainbow" + "\n\n", expected);
    }

    @Test
    public void updateExternalAccounting2() {
        String expected = this.externalAccounting.getSaleLog().getPayment().getChange().toString();
        assertEquals("updateExternalAccounting did not work as expected","40000.0",expected);
    }

}