package se.kth.iv1350.saleProcess.view;
import se.kth.iv1350.saleProcess.controller.Controller;
import se.kth.iv1350.saleProcess.util.Amount;

/**
*    This class is the placeholder for the entire view
*    since the program has no view.
 */


public class View{
    private Controller contr;

    /**
     * Creates a new instance.
     *
     * @param contr The controller that is used for all operations
     */
public View(Controller contr) {

    this.contr = contr;
}

    /**
     * Hard coded calls to systems operations that
     * simulates user inputs.
     */
    public void runFakeSale(){

    contr.startNewSale();

        System.out.println(contr.enterItem(3,1));
        System.out.println(contr.enterItem(1,3));
        System.out.println(contr.enterItem(2,2));
        System.out.println(contr.enterItem(0,1));
        System.out.println(contr.enterItem(3,1));
        System.out.println(contr.allItemsRegistered());
        System.out.println("Customer change: "+ contr.pay(new Amount(70000)) + " kr");
    }
}
