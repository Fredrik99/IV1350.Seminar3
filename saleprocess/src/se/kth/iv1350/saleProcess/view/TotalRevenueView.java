package se.kth.iv1350.saleProcess.view;

import se.kth.iv1350.saleProcess.dbhandler.SaleObserver;
import se.kth.iv1350.saleProcess.util.Amount;

/**
 * This class represents a display that shows the total amount paid (revenue)
 * for purchases since the program started.
 */
public class TotalRevenueView implements SaleObserver {
    private Amount totalRevenue;

    /**
     * Creates an instance and sets the totalRevenue attribute to zero.
     */
    TotalRevenueView(){
        this.totalRevenue = new Amount(0);
    }

    /**
     * Increases the attribute totalRevenue with an <code>Amount<code/> that has been paid.
     *
     * @param amountPaid is the amount that has been paid.
     */
    @Override
    public void newAmountPaid(Amount amountPaid) {
        this.totalRevenue.increaseAmount(amountPaid);
        printCurrentRevenue();
    }

    /**
     * This method prints out the current revenue to the user.
     */
    private void printCurrentRevenue(){
            System.out.println("    #####REVENUE UPDATE#####\n");
        System.out.println("  Current revenue is: " + this.totalRevenue + " kr\n");
        System.out.println("###################################\n");
    }
}
