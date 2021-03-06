/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Mathematics.Vector.Product;

import Mathematics.Vector.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * jUnit test for the class Mathematics.Vector.Product.InnerReal.
 * @author Rune Dahl Iversen
 */
public class InnerRealTest {
    private InnerReal _instance;

    public InnerRealTest() { // Intentional
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        System.out.println("Mathematics.Vector.Product.InnerReal");
    }

    @AfterClass
    public static void tearDownClass() throws Exception { // Intentional
    }

    @Before
    public void setUp() {
        this._instance = new InnerReal();
    }

    @After
    public void tearDown() {
        this._instance = null;
    }

    /**
     * Test of value method, of class InnerReal.
     */
    @Test
    public void testValue() {
        System.out.println("value");
        InnerReal instance = this._instance;

        Vector<Double> firstInput = new VectorReal(3);

        Vector<Double> secondInput = new VectorReal(3);
        Double expResult = 0.0;
        Double result = instance.value(firstInput, secondInput);
        assertEquals(firstInput.toString() + " .*. " + secondInput.toString(), expResult, result);

        firstInput = new VectorReal(3, 1.0);
        expResult = 0.0;
        result = instance.value(firstInput, secondInput);
        assertEquals(firstInput.toString() + " .*. " + secondInput.toString(), expResult, result);

        secondInput = new VectorReal(3, 1.0);
        expResult = 3.0;
        result = instance.value(firstInput, secondInput);
        assertEquals(firstInput.toString() + " .*. " + secondInput.toString(), expResult, result);

        firstInput = firstInput.scale(7.45);
        secondInput = VectorReal.Unit(3, 0);
        expResult = 7.45;
        result = instance.value(firstInput, secondInput);
        assertEquals(firstInput.toString() + " .*. " + secondInput.toString(), expResult, result);

        secondInput = VectorReal.Unit(3, 1);
        expResult = 7.45;
        result = instance.value(firstInput, secondInput);
        assertEquals(firstInput.toString() + " .*. " + secondInput.toString(), expResult, result);

        secondInput = VectorReal.Unit(3, 2);
        expResult = 7.45;
        result = instance.value(firstInput, secondInput);
        assertEquals(firstInput.toString() + " .*. " + secondInput.toString(), expResult, result);
    }

    /**
     * Test of value method, of class InnerReal,
     * for non-conforming vector values (different dimensions).
     */
    @Test (expected=IllegalArgumentException.class)
    public void testValue_NonConformingDimensions() {
        System.out.println("value(different dimensions)");
        InnerReal instance = this._instance;

        Vector<Double> firstInput = new VectorReal(2);
        Vector<Double> secondInput = new VectorReal(3);
        instance.value(firstInput, secondInput);
    }

    /**
     * Test of value method, of class InnerReal,
     * for non-conforming vector values.
     */
    @Test (expected=IllegalArgumentException.class)
    public void testValue_NonConformingFirstDimension() {
        System.out.println("value(different first dimensions)");
        InnerReal instance = this._instance;

        Vector<Double> firstInput = new VectorReal(1, 3);
        Vector<Double> secondInput = new VectorReal(3);
        instance.value(firstInput, secondInput);
    }

    /**
     * Test of value method, of class InnerReal,
     * for null as the first value.
     */
    @Test (expected=NullPointerException.class)
    public void testValue_FirstNull() {
        System.out.println("value(null, v)");
        InnerReal instance = this._instance;

        Vector<Double> firstInput = null;
        Vector<Double> secondInput = new VectorReal(3);
        instance.value(firstInput, secondInput);
    }

    /**
     * Test of value method, of class InnerReal,
     * for null as the second value.
     */
    @Test (expected=NullPointerException.class)
    public void testValue_SecondNull() {
        System.out.println("value(v, null)");
        InnerReal instance = this._instance;

        Vector<Double> firstInput = new VectorReal(3);
        Vector<Double> secondInput = null;
        instance.value(firstInput, secondInput);
    }
}