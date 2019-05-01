package se.kth.iv1350.saleProcess.util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AmountTest {
        private Amount noArgConstraint;
        private Amount amtSetAtTen;
        private Amount amtSetAtNegativeTen;

        @Before
        public void setUp(){
            noArgConstraint = new Amount();
            amtSetAtTen = new Amount(10);
            amtSetAtNegativeTen = new Amount(-10);
        }

        @After
        public void tearDown(){
            noArgConstraint = null;
            amtSetAtTen = null;
        }

        @Test
        public void increaseAmount_TestEqualsAmountPositiveResult() {
            noArgConstraint.increaseAmount(amtSetAtTen);

            assertEquals("The amount does not equal 10",10, noArgConstraint.getAmount(), 0.001);
        }

        @Test
        public void increaseAmount_TestEqualsAmountNegativeResult() {
            noArgConstraint.increaseAmount(amtSetAtNegativeTen);

            assertEquals("The amount does not equal -10",-10, noArgConstraint.getAmount(), 0.001);
        }

        @Test
        public void increaseAmount_TestEqualsAmountZeroResult() {
            amtSetAtTen.increaseAmount(amtSetAtNegativeTen);

            assertEquals("The amount does not equal 0",0, noArgConstraint.getAmount(), 0.001);
        }

        @Test
        public void multiplyAmount_TestPositiveResult() {
            Amount multiplyAmount = new Amount(3);

            multiplyAmount.multiplyAmount(amtSetAtTen);

            assertEquals("The amount does not equal 30",30,multiplyAmount.getAmount(), 0.001);
        }

        @Test
        public void multiplyAmount_TestNegativeResult() {

            amtSetAtNegativeTen.multiplyAmount(amtSetAtTen);

            assertEquals("The amount does not equal -100",-100, amtSetAtNegativeTen.getAmount(), 0.001);
        }

        @Test
        public void multiplyAmount_TestZeroResult() {
            Amount multiplyAmount = new Amount(0);

            multiplyAmount.multiplyAmount(amtSetAtTen);

            assertEquals("The amount does not equal 0",0,multiplyAmount.getAmount(), 0.001);
        }

        @Test
        public void subtractAmount_TestNegativeResult() {
            Amount numerator = new Amount(3);

            noArgConstraint.subtractAmount(numerator, amtSetAtTen);

            assertEquals("The amount does not equal -7", -7,noArgConstraint.getAmount(),0.001);

        }

        @Test
        public void subtractAmount_TestPositiveResult() {
            Amount denominator = new Amount(3);

            noArgConstraint.subtractAmount(amtSetAtTen, denominator);

            assertEquals("The amount does not equal 7", 7,noArgConstraint.getAmount(),0.001);

        }

        @Test
        public void subtractAmount_TestZeroResult() {

            noArgConstraint.subtractAmount(amtSetAtTen, amtSetAtTen);

            assertEquals("The amount does not equal 0", 0,noArgConstraint.getAmount(),0.001);

        }

        @Test
        public void toString_TestPositiveResult() {

            double representedAmt = 33;
            Amount amount = new Amount(representedAmt);
            String expResult = Double.toString(representedAmt);
            String result = amount.toString();

            assertEquals("Wrong string returned by toString", expResult, result);
        }

        @Test
        public void toString_TestNegativeResult() {

            double representedAmt = -33;
            Amount amount = new Amount(representedAmt);
            String expResult = Double.toString(representedAmt);
            String result = amount.toString();

            assertEquals("Wrong string returned by toString", expResult, result);
        }

        @Test
        public void toString_TestZeroResult() {

            double representedAmt = 0;
            Amount amount = new Amount(representedAmt);
            String expResult = Double.toString(representedAmt);
            String result = amount.toString();

            assertEquals("Wrong string returned by toString", expResult, result);
        }

}