package se.kth.iv1350.saleProcess.view;

import se.kth.iv1350.saleProcess.controller.Controller;
import se.kth.iv1350.saleProcess.controller.InvalidIdentifierException;
import se.kth.iv1350.saleProcess.controller.OperationFailedException;
import se.kth.iv1350.saleProcess.util.Amount;
import se.kth.iv1350.saleProcess.util.ExceptionLogger;

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
            System.out.println(controller.enterItem(0, 1));
        } catch (OperationFailedException exception) {
            handleException("Program could not get access to the database");
        } catch (InvalidIdentifierException exception) {
            handleException("There exists no item in registry with the chosen identification number");
        }

        try {
            System.out.println(controller.enterItem(1, 1));
        } catch (OperationFailedException exception) {
            handleException("Program could not get access to the database");
        } catch (InvalidIdentifierException exception) {
            handleException("There exists no item in registry with the chosen identification number");
        }

        try {
            System.out.println(controller.enterItem(2, 1));
        } catch (OperationFailedException exception) {
            handleException("Program could not get access to the database");
        } catch (InvalidIdentifierException exception) {
            handleException("There exists no item in registry with the chosen identification number");
        }

        try {
            System.out.println(controller.enterItem(100, 1));
        } catch (OperationFailedException exception) {
            handleException("Program could not get access to the database");
        } catch (InvalidIdentifierException exception) {
            handleException("There exists no item in registry with the chosen identification number");
        }

        System.out.println(controller.allItemsRegistered());

        System.out.println(this.controller.discountRequest(11));

        System.out.println("Customer change: "+ controller.pay(new Amount(25000)) + " kr\n\n");

        System.out.println(ExceptionLogger.getInstance().getExceptionLog());
    }

    /**
     * When an exception is thrown this method is called to handle that exception.
     *
     * @param exception is the exception causing the call of this method.
     */
    private void handleException(String exception){
        errorMessageHandler.presentErrorMessage(exception);
    }
}
