package se.kth.iv1350.saleProcess.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import se.kth.iv1350.saleProcess.dbhandler.SystemCreator;

import static org.junit.Assert.*;

public class ControllerTest {
    private Controller testController;
    private Exception testInvalidIdentifierException;
    private Exception testOperationFailedException;
    private RuntimeException runtimeException;

    @Before
    public void setUp() {
        this.testController = new Controller(new SystemCreator());
        this.testInvalidIdentifierException = new InvalidIdentifierException(12345);
        this.runtimeException = new RuntimeException("Database failure");
        this.testOperationFailedException = new OperationFailedException("Program could not get access to the database", this.runtimeException);
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

}