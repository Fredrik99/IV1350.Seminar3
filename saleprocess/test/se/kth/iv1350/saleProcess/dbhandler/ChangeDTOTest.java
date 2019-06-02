package se.kth.iv1350.saleProcess.dbhandler;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import se.kth.iv1350.saleProcess.util.Amount;

import static org.junit.Assert.*;

public class ChangeDTOTest {
    private ChangeDTO change;


    @Before
    public void setUp(){
        change = new ChangeDTO(new Amount(1000), new Amount(500));
    }

    @After
    public void tearDown()  {
        change = null;
    }

    @Test
    public void toStringTestLargeAmount() {

        String result = change.toString();
        assertEquals("The result is not 500", "500.0", result);
    }

    @Test
    public void toStringTestSmallAmount() {
        change = new ChangeDTO(new Amount(2), new Amount(1));
        String result = change.toString();
        assertEquals("The result is not 1", "1.0", result);
    }

}