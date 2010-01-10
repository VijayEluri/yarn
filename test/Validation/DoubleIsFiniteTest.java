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
 * Test of the DoubleIsFinite class.
 * @author Rune Dahl Iversen
 */
public class DoubleIsFiniteTest {
    private ValidatorTest<Double> _tester;
    private DoubleIsFinite _instance;
    private Double[] _values;

    public DoubleIsFiniteTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        this._tester = new ValidatorTest<Double>();
        this._instance = new DoubleIsFinite();
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
        this._instance = null;
        this._values = null;
    }

    /**
     * Test of Message method, of class DoubleIsNumeric.
     */
    @Test
    public void testMessage() {
        System.out.println("Testing DoubleIsFinite.Message(...).");
        this._tester.testMessage(this._instance, this._values[0],
                Double.toString(this._values[0]),
                "-Infinity is not finite.");
        this._tester.testMessage(this._instance, this._values[1],
                Double.toString(this._values[1]), "");
        this._tester.testMessage(this._instance, this._values[2],
                Double.toString(this._values[2]), "");
        this._tester.testMessage(this._instance, this._values[3],
                Double.toString(this._values[3]), "");
        this._tester.testMessage(this._instance, this._values[4],
                Double.toString(this._values[4]),
                "Infinity is not finite.");
        this._tester.testMessage(this._instance, this._values[5],
                Double.toString(this._values[5]), "");
        this._tester.testMessage(this._instance, this._values[6],
                "null", "null is not finite.");
    }

    /**
     * Test of Validate method, of class DoubleIsNumeric.
     */
    @Test
    public void testValidate() {
        System.out.println("Testing DoubleIsFinite.Validate(...)");
        this._tester.testValidate(this._instance, this._values[0], false);
        this._tester.testValidate(this._instance, this._values[1], true);
        this._tester.testValidate(this._instance, this._values[2], true);
        this._tester.testValidate(this._instance, this._values[3], true);
        this._tester.testValidate(this._instance, this._values[4], false);
        this._tester.testValidate(this._instance, this._values[5], true);
        this._tester.testValidate(this._instance, this._values[6], false);
    }
}