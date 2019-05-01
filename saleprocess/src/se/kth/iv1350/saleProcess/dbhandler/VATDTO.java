package se.kth.iv1350.saleProcess.dbhandler;

import se.kth.iv1350.saleProcess.util.Amount;

/**
 * A class containing the VAT-rates of the items in the application.
 */
public final class VATDTO {

    private final double rate1 = 0.25;
    private final double rate2 = 0.12;
    private final double rate3 = 0.06;
    private Amount itemVAT;

    /**
     * Creates an instance of the class and sets the items VAT-rate.
     */
    public VATDTO(int identifier){

        this.itemVAT = new Amount();
        setItemVAT(identifier);

    }

    /**
     * @return Returns the items VAT-rate.
     */
    public Amount getItemVAT(){
        return itemVAT;
    }

    /**
     * Sets the <>ItemDTO</> object typoes VAT-rate according to its identifier.
     * @param identifier The identifier through the VAT-rate is set.
     */
    private void setItemVAT(int identifier){

        if(identifier % 3 == 0)
            this.itemVAT = new Amount(rate1);
        else if (identifier % 3 == 1)
            this.itemVAT = new Amount(rate2);
        else
            this.itemVAT = new Amount(rate3);
    }
}
