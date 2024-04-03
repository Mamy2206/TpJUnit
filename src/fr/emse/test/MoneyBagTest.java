package fr.emse.test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class MoneyBagTest {
    private Money f12CHF, f14CHF, f7USD, f21USD;
    private MoneyBag fMB1, fMB2;

    @Before
    public void setUp() {
        f12CHF = new Money(12, "CHF");
        f14CHF = new Money(14, "CHF");
        f7USD = new Money(7, "USD");
        f21USD = new Money(21, "USD");
        fMB1 = new MoneyBag(f12CHF, f7USD);
        fMB2 = new MoneyBag(f14CHF, f21USD);
    }

    @Test
    public void testBagEquals() {
        assertTrue(!fMB1.equals(null));
        assertEquals(fMB1, fMB1);
        assertTrue(!fMB1.equals(f12CHF));
        assertTrue(!f12CHF.equals(fMB1));
        assertTrue(!fMB1.equals(fMB2));
    }

    @Test
    public void testMixedSimpleAdd() {
        Money[] bag = {f12CHF, f7USD};
        MoneyBag expected = new MoneyBag(bag);
        assertEquals(expected, f12CHF.add(f7USD));
    }

    @Test
    public void testBagSimpleAdd() {
        MoneyBag expectedMB1 = new MoneyBag(f12CHF, f7USD);
        assertEquals(expectedMB1, f12CHF.add(f7USD));
    }

    @Test
    public void testSimpleBagAdd() {
        Money expected = new Money(19, "CHF");
        assertEquals(expected, f12CHF.add(fMB2));
    }

    @Test
    public void testBagBagAdd() {
        Money[] expectedBag = {new Money(26, "CHF"), new Money(28, "USD")};
        MoneyBag expected = new MoneyBag(expectedBag);
        assertEquals(expected, fMB1.add(fMB2));
    }

    @Test
    public void testSimplificationAdd() {
        MoneyBag mb = new MoneyBag(new Money(22, "CHF"));
        assertEquals(new Money(34, "CHF"), f12CHF.add(mb));
    }
}