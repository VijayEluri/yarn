/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Time.Periods;

import java.util.GregorianCalendar;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * jUnit-test for the class Time.Periods.GregorianMilliSecond.
 * @author Rune Dahl Iversen
 */
public class GregorianMilliSecondTest {
    private GregorianMilliSecond _negativeOne;
    private GregorianMilliSecond _one;
    private GregorianMilliSecond _two;

    public GregorianMilliSecondTest() { // Intentional
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        System.out.println("Time.Periods.GregorianMilliSecond");
    }

    @AfterClass
    public static void tearDownClass() throws Exception { // Intentional
    }

    @Before
    public void setUp() {
        this._negativeOne = new GregorianMilliSecond(-1);
        this._one = new GregorianMilliSecond(1);
        this._two = new GregorianMilliSecond(2);
    }

    @After
    public void tearDown() {
        this._negativeOne = null;
        this._one = null;
        this._two = null;
    }

    /**
     * Test of getCount method, of class GregorianMilliSecond.
     */
    @Test
    public void testGetCount() {
        System.out.println("getCount");
        assertEquals(-1, this._negativeOne.getCount());
        assertEquals(1, this._one.getCount());
        assertEquals(2, this._two.getCount());
    }

    /**
     * Test of setCount method, of class GregorianMilliSecond.
     */
    @Test
    public void testSetCount() {
        System.out.println("setCount");
        int count = 17;

        this._negativeOne.setCount(count);
        this._one.setCount(count);
        this._two.setCount(count);

        assertEquals(17, this._negativeOne.getCount());
        assertEquals(17, this._one.getCount());
        assertEquals(17, this._two.getCount());
    }

    /**
     * Test of shift method, of class GregorianMilliSecond.
     */
    @Test
    public void testShift() {
        System.out.println("shift");
        GregorianCalendar date = new GregorianCalendar(2000, 2, 1);
        int count = 15;
        GregorianCalendar expResult = new GregorianCalendar(2000, 1, 29, 23, 59, 59);
        expResult.add(GregorianCalendar.MILLISECOND, 985);
        GregorianCalendar result = this._negativeOne.shift(date, count);
        assertEquals(expResult, result);

        expResult = new GregorianCalendar(2000, 2, 1, 0, 0, 0);
        expResult.add(GregorianCalendar.MILLISECOND, 15);
        result = this._one.shift(date, count);
        assertEquals(expResult, result);

        expResult = new GregorianCalendar(2000, 2, 1, 0, 0, 0);
        expResult.add(GregorianCalendar.MILLISECOND, 30);
        result = this._two.shift(date, count);
        assertEquals(expResult, result);
    }

    /**
     * Test of shift method, of class GregorianMilliSecond, for a null date.
     */
    @Test (expected=NullPointerException.class)
    public void testShift_NullDate() {
        System.out.println("shift(null, i)");
        int count = 15;
        this._negativeOne.shift(null, count);
        fail("No exception thrown.");
    }
}