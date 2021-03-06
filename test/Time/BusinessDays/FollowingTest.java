/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Time.BusinessDays;

import Time.Holidays.*;
import Time.Periods.*;
import java.util.GregorianCalendar;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit test for the class Time.BusinessDays.Following.
 * @author Rune Dahl Iversen
 */
public class FollowingTest {
    private Following<GregorianCalendar> _following;
    private Holiday<GregorianCalendar> _holidays;
    private Period<GregorianCalendar> _day;

    public FollowingTest() { // Intentional
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        System.out.println("Time.BusinessDays.Following");
    }

    @AfterClass
    public static void tearDownClass() throws Exception { // Intentional
    }

    @Before
    public void setUp() {
        this._day = new GregorianDay(1);
        this._holidays = new GregorianWeekday(GregorianCalendar.SUNDAY);
        this._following = new Following<GregorianCalendar>(this._holidays, this._day);
    }

    @After
    public void tearDown() {
        this._day = null;
        this._holidays = null;
        this._following = null;
    }

    /**
     * Test of getHolidays method, of class Following.
     */
    @Test
    public void testGetHolidays() {
        System.out.println("getHolidays");
        Following<GregorianCalendar> instance = this._following;
        Holiday<GregorianCalendar> holiday = instance.getHolidays();
        assertNotNull(holiday);
        if (holiday instanceof GregorianWeekday) {
            GregorianWeekday gwd = (GregorianWeekday)holiday;
            assertEquals("Wrong weekday from returned holidays.",
                    GregorianCalendar.SUNDAY, gwd.getWeekday());
        } else
            fail("Wrong class of holidays returned.");
    }

    /**
     * Test of setHolidays method, of class Following.
     */
    @Test
    public void testSetHolidays() {
        System.out.println("setHolidays");
        Following<GregorianCalendar> instance = this._following;
        Holiday<GregorianCalendar> holiday = instance.getHolidays();
        assertNotNull(holiday);
        if (holiday instanceof GregorianWeekday) {
            GregorianWeekday gwd = (GregorianWeekday)holiday;
            assertEquals("Pre-condition: Wrong weekday from returned holidays.",
                    GregorianCalendar.SUNDAY, gwd.getWeekday());
        } else
            fail("Pre-condition: Wrong class of holidays returned.");

        instance.setHolidays(new GregorianWesternEaster());

        holiday = instance.getHolidays();
        assertNotNull(holiday);
        if (holiday instanceof GregorianWesternEaster)
            assertTrue(true);
        else
            fail("Wrong class of holidays returned after setHolidays.");
    }

    /**
     * Test of setHolidays method, of class Following, for the value null.
     */
    @Test (expected=NullPointerException.class)
    public void testSetHolidaysNull() {
        System.out.println("setHolidays(null)");
        this._following.setHolidays(null);
        fail("No exception thrown.");
    }

    /**
     * Test of getPeriod method, of class Following.
     */
    @Test
    public void testGetPeriod() {
        System.out.println("getPeriod");
        Following<GregorianCalendar> instance = this._following;
        Period<GregorianCalendar> period = instance.getPeriod();
        assertNotNull(period);
        if (period instanceof GregorianDay) {
            GregorianDay gd = (GregorianDay)period;
            assertEquals("Wrong count from returned period.", 1, gd.getCount());
        } else
            fail("Wrong class of period returned.");
    }

    /**
     * Test of setPeriod method, of class Following.
     */
    @Test
    public void testSetPeriod() {
        System.out.println("setPeriod");
        Following<GregorianCalendar> instance = this._following;
        Period<GregorianCalendar> period = instance.getPeriod();
        assertNotNull(period);
        if (period instanceof GregorianDay) {
            GregorianDay gd = (GregorianDay)period;
            assertEquals("Precondition: Wrong count from returned period.", 1, gd.getCount());
        } else
            fail("Pre-condition: Wrong class of period returned.");

        instance.setPeriod(new GregorianWeek(2));

        period = instance.getPeriod();
        assertNotNull(period);
        if (period instanceof GregorianWeek) {
            GregorianWeek gd = (GregorianWeek)period;
            assertEquals("Wrong count from returned period after setPeriod.",
                    2, gd.getCount());
        } else
            fail("Wrong class of period returned after setPeriod.");
    }

    /**
     * Test of setPeriod method, of class Following, for the value null.
     */
    @Test (expected=NullPointerException.class)
    public void testGetPeriodNull() {
        System.out.println("setPeriod(null)");
        this._following.setPeriod(null);
        fail("No exception thrown.");
    }

    /**
     * Test of Adjust method, of class Following.
     */
    @Test
    public void testAdjust() {
        System.out.println("Adjust");
        Following<GregorianCalendar> instance = this._following;
        GregorianCalendar dateTime = new GregorianCalendar(2000, 0, 1);
        for (int i = 0; i < 1500; i++) {
            GregorianCalendar expResult = (GregorianCalendar)dateTime.clone();
            if (expResult.get(GregorianCalendar.DAY_OF_WEEK) == GregorianCalendar.SUNDAY)
                expResult.add(GregorianCalendar.DAY_OF_YEAR, 1);
            GregorianCalendar result = instance.adjust(dateTime);
            assertEquals(expResult, result);
            dateTime.add(GregorianCalendar.DAY_OF_YEAR, 1);
        }
    }

    /**
     * Test of Adjust method, of class Following, for the value null.
     */
    @Test (expected=NullPointerException.class)
    public void testAdjustNull() {
        System.out.println("Adjust(null)");
        GregorianCalendar dateTime = null;
        Following<GregorianCalendar> instance = this._following;
        instance.adjust(dateTime);
        fail("No exception thrown.");
    }

    /**
     * Test of Shift method, of class Following.
     */
    @Test
    public void testShift() {
        System.out.println("Shift");
        Following<GregorianCalendar> instance = this._following;
        GregorianCalendar dateTime = new GregorianCalendar(2000, 0, 1);
        for (int i = 0; i < 100; i++) {
            for (int count = -12; count < 14; count++) {
                GregorianCalendar expResult = (GregorianCalendar)dateTime.clone();
                for (int j = 0; j < Math.abs(count); j++) {
                    expResult.add(GregorianCalendar.DAY_OF_YEAR, (int)Math.signum(count));
                    if (expResult.get(GregorianCalendar.DAY_OF_WEEK) == GregorianCalendar.SUNDAY)
                        expResult.add(GregorianCalendar.DAY_OF_YEAR, (int)Math.signum(count));
                }
                GregorianCalendar result = instance.shift(dateTime, count);
                assertEquals(expResult, result);
            }
            dateTime.add(GregorianCalendar.DAY_OF_YEAR, 1);
        }
    }

    /**
     * Test of Shift method, of class Following, for a null date/time value.
     */
    @Test (expected=NullPointerException.class)
    public void testShiftNull() {
        System.out.println("Shift(null, x)");
        GregorianCalendar dateTime = null;
        int count = 2;
        Following<GregorianCalendar> instance = this._following;
        instance.shift(dateTime, count);
        fail("No exception thrown.");
    }
}