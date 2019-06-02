package se.kth.iv1350.saleProcess.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import se.kth.iv1350.saleProcess.dbhandler.SaleObserver;
import se.kth.iv1350.saleProcess.dbhandler.SystemCreator;
import se.kth.iv1350.saleProcess.util.Amount;
import se.kth.iv1350.saleProcess.view.TotalRevenueView;

import static org.junit.Assert.*;

public class ControllerTest {
    private Controller testController;
    private Exception testInvalidIdentifierException;
    private Exception testOperationFailedException;
    private RuntimeException runtimeException;
    private TotalRevenueView totalRevenueView;
    private SaleObserver observer;

    @Before
    public void setUp() {
        this.testController = new Controller(new SystemCreator());
        this.testController.addSaleObserver(new TotalRevenueView());
        this.testInvalidIdentifierException = new InvalidIdentifierException(12345);
        this.runtimeException = new RuntimeException("Database failure");
        this.testOperationFailedException = new OperationFailedException("Program could not get access to the database", this.runtimeException);
        this.totalRevenueView = new TotalRevenueView();
        this.observer = totalRevenueView;
    }

    @After
    public void tearDown() {
        this.testController = null;
        this.testInvalidIdentifierException = null;
        this.runtimeException = null;
        this.testOperationFailedException = null;
    }

    @Test
    public void enterItemTestInvalidIdentifierException() {

        int itemID = 12345;
        int quantity = 45;

        try{
            this.testController.enterItem(itemID, quantity);
            fail("The itemID exists in inventory");
        }
        catch (OperationFailedException | InvalidIdentifierException exception){
            assertEquals("The messages are not equal!",this.testInvalidIdentifierException.getMessage(), exception.getMessage());
        }
    }


    @Test
    public void enterItemTestOperationFailedException() {

        int itemID = 100;
        int quantity = 111112;

        try{
            this.testController.enterItem(itemID, quantity);
            fail("No database failure!");
        }
        catch (OperationFailedException | InvalidIdentifierException exception){
            assertEquals("The messages are not equal!",this.testOperationFailedException.getMessage(), exception.getMessage());
        }
    }

    @Test
    public void startNewSale() {
        testController.startNewSale();
        assertEquals("The number of quantities are not zero",0, testController.getCurrentSale().getQuantities().length);
        assertEquals("The number of items are not zero",0, testController.getCurrentSale().getItemDTOS().length);
    }

    @Test
    public void enterItem() {

        testController.startNewSale();

       try {
         testController.enterItem(1, 3);
       }
       catch (OperationFailedException | InvalidIdentifierException exception) {
           fail("enterItem did not work as intended!");
       }

       assertEquals("The quantity is not 3",3,testController.getCurrentSale().getQuantities()[0]);
       assertEquals("The item ID is not 1",1,testController.getCurrentSale().getItemDTOS()[0].getIdentifier());
    }

    @Test
    public void allItemsRegistered() {

        testController.startNewSale();

        try {
            testController.enterItem(0, 1);
        }
        catch (OperationFailedException | InvalidIdentifierException exception) {
            fail("enterItem did not work as intended!");
        }

        assertEquals("The total price is not 8750","Total price(including VAT): " + "8750.0\n", testController.allItemsRegistered());
    }

    @Test
    public void allItemsRegisteredLargeAmount() {

        testController.startNewSale();

        try {
            testController.enterItem(3, 1000);
        }
        catch (OperationFailedException | InvalidIdentifierException exception) {
            fail("enterItem did not work as intended!");
        }

        assertEquals("The total price is not 8750","Total price(including VAT): " + "3750000.0\n", testController.allItemsRegistered());
    }

    @Test
    public void pay() {

        testController.startNewSale();

        try {
            testController.enterItem(2, 2);
        }
        catch (OperationFailedException | InvalidIdentifierException exception) {
            fail("enterItem did not work as intended!");
        }

        testController.pay(new Amount(20000));

        String expected = testController.getPayment().getChange().toString();

       assertEquals("The change is not 9400", "9400.0" , expected);

    }

    @Test
    public void payLargeAmount() {

        testController.startNewSale();

        try {
            testController.enterItem(0, 11234);
        }
        catch (OperationFailedException | InvalidIdentifierException exception) {
            fail("enterItem did not work as intended!");
        }

        testController.pay(new Amount(100000000));

        String expected = testController.getPayment().getChange().toString();

        assertEquals("The change is not 1702500.0", "1702500.0" , expected);

    }

    @Test
    public void addSaleObserver() {

        testController.addSaleObserver(observer);
        testController.startNewSale();

        try {
            testController.enterItem(0, 1);
        }
        catch (OperationFailedException | InvalidIdentifierException exception) {
            fail("enterItem did not work as intended!");
        }

        testController.pay(new Amount(10000));

        assertEquals("The revenue is not 8750", 8750.0 ,totalRevenueView.getTotalRevenue().getAmount(), 0.001);
    }

    @Test
    public void addSaleObserverLargeAmount() {

        testController.addSaleObserver(observer);
        testController.startNewSale();

        try {
            testController.enterItem(3, 100);
        }
        catch (OperationFailedException | InvalidIdentifierException exception) {
            fail("enterItem did not work as intended!");
        }

        testController.pay(new Amount(500000));

        assertEquals("The revenue is not 375000", 375000.0,totalRevenueView.getTotalRevenue().getAmount(), 0.001);
    }
}