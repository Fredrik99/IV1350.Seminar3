package se.kth.iv1350.saleProcess.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import se.kth.iv1350.saleProcess.util.Amount;

import static org.junit.Assert.*;

public class PaymentTest {
    private Payment payment;

    @Before
    public void setUp(){
    }

    @After
    public void tearDown(){
        payment = null;
    }

    @Test
    public void completeSale() {
    }

    @Test
    public void getPaidAmountLarge() {
        payment = new Payment(new Amount(5000000), new Amount(3000));
        assertEquals("Paid amount does not equal 5000000", 5000000.0, payment.getPaidAmount().getAmount(), 0.001);
    }

    @Test
    public void getPaidAmountSmall() {
        payment = new Payment(new Amount(1), new Amount(3000));
        assertEquals("Paid amount does not equal 1", 1.0, payment.getPaidAmount().getAmount(), 0.001);
    }

    @Test
    public void getChangeSmall() {
        payment = new Payment(new Amount(2), new Amount(1));
        assertEquals("Change does not equal 1", "1.0", payment.getChange().toString());
    }

    @Test
    public void getChangeLarge() {
        payment = new Payment(new Amount(700000), new Amount(1));
        assertEquals("Change does not equal 699999", "699999.0", payment.getChange().toString());
    }


}