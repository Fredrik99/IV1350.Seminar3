package se.kth.iv1350.saleProcess.dbhandler;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StoreDTOTest {
    private StoreDTO storeDTO;

    @Before
    public void setUp() {
        storeDTO = new StoreDTO();
    }

    @After
    public void tearDown() {
        storeDTO = null;
    }

    @Test
    public void toStringTest() {

        assertEquals("The stores name and address is not the expected!","Store name: " + "Epic Store" + "\n\n"
                                + "Store address: " + "At the end of the rainbow" + "\n\n", storeDTO.toString());
    }
}