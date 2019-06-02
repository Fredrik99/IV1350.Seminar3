package se.kth.iv1350.saleProcess.dbhandler;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import se.kth.iv1350.saleProcess.util.Amount;
import se.kth.iv1350.saleProcess.view.TotalRevenueView;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CashRegisterTest {
    private CashRegister cashRegister;
    private Amount addAmount;
    private SaleObserver observer;
    private List<SaleObserver> saleObservers;
    private TotalRevenueView totalRevenueView;

    @Before
    public void setUp() {
        cashRegister = new CashRegister();
        addAmount = new Amount();
        totalRevenueView = new TotalRevenueView();
        observer = totalRevenueView;
        saleObservers = new ArrayList<>();
    }

    @After
    public void tearDown() {
        cashRegister = null;
        addAmount = null;
        observer = null;
        saleObservers = null;
    }

    @Test
    public void addSmallPayment() {
        Amount amount = new Amount(5);
        addAmount.increaseAmount(amount);
        cashRegister.addPayment(addAmount);
        assertEquals("CashRegister amount does not equal 100005",100005,  cashRegister.getAmountPresent().getAmount(), 0.001);
    }

    @Test
    public void addLargePayment() {
        Amount amount = new Amount(250000);
        addAmount.increaseAmount(amount);
        cashRegister.addPayment(addAmount);
        assertEquals("CashRegister amount does not equal 350000", 350000, cashRegister.getAmountPresent().getAmount(), 0.001);
    }

    @Test
    public void subtractSmallPayment() {
        Amount amount = new Amount(-15);
        addAmount.increaseAmount(amount);
        cashRegister.addPayment(amount);
        assertEquals("CashRegister amount does not equal 99985", 99985, cashRegister.getAmountPresent().getAmount(), 0.001);
    }

    @Test
    public void subtractLargePayment() {
        Amount amount = new Amount(-90000);
        addAmount.increaseAmount(amount);
        cashRegister.addPayment(amount);
        assertEquals("CashRegister amount does not equal 10000", 10000, cashRegister.getAmountPresent().getAmount(), 0.001);
    }

    @Test
    public void notifyObserversLargeAmount() {
        saleObservers.add(observer);
        cashRegister.addSaleObservers(saleObservers);
        Amount amount = new Amount(250000);
        addAmount.increaseAmount(amount);
        cashRegister.addPayment(addAmount);
        assertEquals("Revenue does not equal 250000",250000,totalRevenueView.getTotalRevenue().getAmount(),0.001);

    }

    @Test
    public void notifyObserversSmallAmount() {
        saleObservers.add(observer);
        cashRegister.addSaleObservers(saleObservers);
        Amount amount = new Amount(1);
        addAmount.increaseAmount(amount);
        cashRegister.addPayment(addAmount);
        assertEquals("Revenue does not equal 1",1,totalRevenueView.getTotalRevenue().getAmount(),0.001);

    }

}