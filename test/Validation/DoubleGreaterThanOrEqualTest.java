/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Validation;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test of the DoubleGreaterThanOrEqual class.
 * @author Rune Dahl Iversen
 */
public class DoubleGreaterThanOrEqualTest {
    private ValidatorTest<Double> _tester;
    private LimitBasedTest<Double> _limit;
    private DoubleGreaterThanOrEqual _instance;
    private Double[] _values;

    public DoubleGreaterThanOrEqualTest() { // Intentional
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        System.out.println("Validation.DoubleGreaterThanOrEqual");
    }

    @AfterClass
    public static void tearDownClass() throws Exception { // Intentional
    }

    @Before
    public void setUp() {
        this._tester = new ValidatorTest<Double>();
        this._limit = new LimitBasedTest<Double>();
        this._instance = new DoubleGreaterThanOrEqual();
        this._values = new Double[7];
        this._values[0] = Double.NEGATIVE_INFINITY;
        this._values[1] = -1.0;
        this._values[2] = 0.0;
        this._values[3] = 1.0;
        this._values[4] = Double.POSITIVE_INFINITY;
        this._values[5] = Double.NaN;
        this._values[6] = null;
    }

    @After
    public void tearDown() {
        this._tester = null;
        this._limit = null;
        this._instance = null;
        this._values = null;
    }

    /**
     * Test of the specified constructor, of class DoubleGreaterThanOrEqual.
     */
    @Test
    public void testSpecifiedCtor() {
        System.out.println("Testing specified ctor of DoubleGreaterThanOrEqual.");
        DoubleGreaterThanOrEqual instance = new DoubleGreaterThanOrEqual(9.0);
        this._limit.testGetLimit(instance, 9.0);
    }

    /**
     * Test of getLimit method, of class DoubleGreaterThanOrEqual.
     */
    @Test
    public void testGetLimit() {
        System.out.println("getLimit()");
        this._limit.testGetLimit(this._instance, 0.0);
    }

    /**
     * Test of setLimit method, of class DoubleGreaterThanOrEqual.
     */
    @Test
    public void testSetLimit() {
        System.out.println("setLimit");
        this._limit.testSetLimit(this._instance, 1.0);
    }

    /**
     * Test of setLimit method, of class DoubleGreaterThanOrEqual.
     * Setting the limit to Double.NaN.
     */
    @Test
    public void testSetLimitToNaN() {
        System.out.println("setLimit(Double.NaN)");
        this._limit.testSetLimit(this._instance, Double.NaN);
    }

    /**
     * Test of setLimit method, of class DoubleGreaterThanOrEqual.
     * Setting the limit to null.
     * This is expected to cause a NullPointerException.
     */
    @Test(expected=NullPointerException.class)
    public void testSetLimitToNull() {
        System.out.println("setLimit(null)");
        this._limit.testSetLimit(this._instance, null);
    }

    /**
     * Test of message method, of class DoubleGreaterThanOrEqual.
     */
    @Test
    public void testMessage() {
        System.out.println("Testing DoubleGreaterThanOrEqual.message(...).");
        this._tester.testMessage(this._instance, this._values[0],
                Double.toString(this._values[0]),
                "-Infinity must be greater than or equal to 0.0.");
        this._tester.testMessage(this._instance, this._values[1],
                Double.toString(this._values[1]),
                "-1.0 must be greater than or equal to 0.0.");
        this._tester.testMessage(this._instance, this._values[2],
                Double.toString(this._values[2]), "");
        this._tester.testMessage(this._instance, this._values[3],
                Double.toString(this._values[3]), "");
        this._tester.testMessage(this._instance, this._values[4],
                Double.toString(this._values[4]), "");
        this._tester.testMessage(this._instance, this._values[5],
                Double.toString(this._values[5]),
                "NaN must be greater than or equal to 0.0.");
        this._tester.testMessage(this._instance, this._values[6],
                "null", "null must be greater than or equal to 0.0.");
    }

    /**
     * Test of isValid method, of class DoubleGreaterThanOrEqual.
     */
    @Test
    public void testIsValid() {
        System.out.println("isValid");
        this._tester.testIsValid(this._instance, this._values[0], false);
        this._tester.testIsValid(this._instance, this._values[1], false);
        this._tester.testIsValid(this._instance, this._values[2], true);
        this._tester.testIsValid(this._instance, this._values[3], true);
        this._tester.testIsValid(this._instance, this._values[4], true);
        this._tester.testIsValid(this._instance, this._values[5], false);
        this._tester.testIsValid(this._instance, this._values[6], false);
    }
}