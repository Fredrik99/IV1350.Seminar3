package se.kth.iv1350.saleProcess.startup;
import se.kth.iv1350.saleProcess.dbhandler.SystemCreator;
import se.kth.iv1350.saleProcess.view.View;

/**
 * Contains the <code>main</code>
 * method and performs all startup operations of the application.
 */

public class Main {

    /**
     * Starts the application.
     *
     * @param args The application does not take any command line parameters.
     */
    public static void main(String[] args){

        SystemCreator systemCreator = new SystemCreator();
        se.kth.iv1350.saleProcess.controller.Controller controller = new se.kth.iv1350.saleProcess.controller.Controller(systemCreator);
        View view = new View(controller);

        view.runFakeSale();
    }
}