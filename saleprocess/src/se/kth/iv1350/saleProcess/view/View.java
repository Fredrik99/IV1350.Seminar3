package se.kth.iv1350.saleProcess.view;

import se.kth.iv1350.saleProcess.controller.Controller;
import se.kth.iv1350.saleProcess.controller.InvalidIdentifierException;
import se.kth.iv1350.saleProcess.controller.OperationFailedException;
import se.kth.iv1350.saleProcess.util.Amount;

/**
*    This class is the placeholder for the entire view
*    since the program has no view.
 */
public class View {
    private Controller controller;
    private ErrorMessageHandler errorMessageHandler;

    /**
     * Creates a new instance.
     *
     * @param controller The controller that is used for all operations
     */
public View(Controller controller) {

    this.controller = controller;
    this.controller.addSaleObserver(new TotalRevenueView());
    this.errorMessageHandler = new ErrorMessageHandler();
}

    /**
     * Hard coded calls to systems operations that
     * simulates user inputs.
     */
    public void runFakeSale(){

    controller.startNewSale();

    try {
        System.out.println(controller.enterItem(100, 1));
    }
    catch (OperationFailedException | InvalidIdentifierException exception){
        handleException(exception);
    }

        try {
        System.out.println(controller.enterItem(99,3));
    }
    catch (OperationFailedException | InvalidIdentifierException exception){
        handleException(exception);
    }

        try {
        System.out.println(controller.enterItem(1,3));
    }
    catch (OperationFailedException | InvalidIdentifierException exception){
        handleException(exception);
    }

        System.out.println(controller.allItemsRegistered());
    System.out.println("Customer change: "+ controller.pay(new Amount(70000)) + " kr");




        controller.startNewSale();

        try {
            System.out.println(controller.enterItem(100, 1));
        }
        catch (OperationFailedException | InvalidIdentifierException exception){
            handleException(exception);
        }

        try {
            System.out.println(controller.enterItem(99,3));
        }
        catch (OperationFailedException | InvalidIdentifierException exception){
            handleException(exception);
        }

        try {
            System.out.println(controller.enterItem(1,3));
        }
        catch (OperationFailedException | InvalidIdentifierException exception){
            handleException(exception);
        }

        System.out.println(controller.allItemsRegistered());
        System.out.println("Customer change: "+ controller.pay(new Amount(70000)) + " kr");
    }

    /**
     * When an exception is thrown this method is called to handle that exception.
     *
     * @param exception is the exception causing the call of this method.
     */
    private void handleException(Exception exception){
        errorMessageHandler.presentErrorMessage(exception);
    }
}
