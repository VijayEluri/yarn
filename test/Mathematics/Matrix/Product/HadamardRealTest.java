/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Mathematics.Matrix.Product;

import Mathematics.Function.Operator;
import Mathematics.Matrix.*;
import Mathematics.Vector.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * jUnit test for the class Mathematics.Matrix.Product.HadamardReal.
 * @author Rune Dahl Iversen
 */
public class HadamardRealTest {
    private HadamardReal _instance;

    public HadamardRealTest() { // Intentional
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        System.out.println("Mathematics.Matrix.Product.HadamardReal");
    }

    @AfterClass
    public static void tearDownClass() throws Exception { // Intentional
    }

    @Before
    public void setUp() {
        this._instance = new HadamardReal();
    }

    @After
    public void tearDown() {
        this._instance = null;
    }

    /**
     * Test of value method, of class HadamardReal.
     */
    @Test
    public void testValue() {
        System.out.println("value");
        Operator<Matrix<Double>, Matrix<Double>, Matrix<Double>> instance = this._instance;

        Matrix<Double> firstInput = MatrixReal.Identity(0, 3);
        Matrix<Double> secondInput = MatrixReal.Identity(0, 3);
        Matrix<Double> expResult = MatrixReal.Identity(0, 3);
        Matrix<Double> result = instance.value(firstInput, secondInput);
        assertEquals(firstInput.toString() + " * " + secondInput.toString(), expResult, result);

        firstInput = MatrixReal.Identity(0, 3);
        secondInput.setColumn(2, new VectorReal(3, 4.5));
        expResult.setValue(2, 2, 4.5);
        result = instance.value(firstInput, secondInput);
        assertEquals(firstInput.toString() + " * " + secondInput.toString(), expResult, result);

        double[][] values = new double[3][3];
        double[][] values2 = new double[3][3];
        double value = 1.0;
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++) {
                values[row][col] = value;
                values2[row][col] = value*value;
                value++;
            }
        firstInput = new MatrixReal(values);
        secondInput = new MatrixReal(values);
        expResult = new MatrixReal(values2);
        result = instance.value(firstInput, secondInput);
        assertEquals(firstInput.toString() + " * " + secondInput.toString(), expResult, result);
    }

    /**
     * Test of value method, of class HadamardReal,
     * for the first value as null.
     */
    @Test (expected=NullPointerException.class)
    public void testValue_FirstNull() {
        System.out.println("value(null, m)");
        Matrix<Double> firstInput = null;
        Matrix<Double> secondInput = MatrixReal.Identity(0, 3);
        HadamardReal instance = this._instance;
        instance.value(firstInput, secondInput);
    }

    /**
     * Test of value method, of class HadamardReal,
     * for the second value as null.
     */
    @Test (expected=NullPointerException.class)
    public void testValue_SecondNull() {
        System.out.println("value(m, null)");
        Matrix<Double> firstInput = MatrixReal.Identity(0, 3);
        Matrix<Double> secondInput = null;
        HadamardReal instance = this._instance;
        instance.value(firstInput, secondInput);
    }

    /**
     * Test of value method, of class HadamardReal,
     * for mismatched first columns.
     */
    @Test (expected=ArithmeticException.class)
    public void testValue_WrongFirstColumns() {
        System.out.println("value(wrong first columns)");
        Matrix<Double> firstInput = MatrixReal.Value(0, 3, 1, 3, 4.5);
        Matrix<Double> secondInput = MatrixReal.Identity(0, 3);
        HadamardReal instance = this._instance;
        instance.value(firstInput, secondInput);
    }

    /**
     * Test of value method, of class HadamardReal,
     * for mismatched columns.
     */
    @Test (expected=ArithmeticException.class)
    public void testValue_WrongColumns() {
        System.out.println("value(wrong columns)");
        Matrix<Double> firstInput = MatrixReal.Value(0, 3, 0, 4, 4.5);
        Matrix<Double> secondInput = MatrixReal.Identity(0, 3);
        HadamardReal instance = this._instance;
        instance.value(firstInput, secondInput);
    }

    /**
     * Test of value method, of class HadamardReal,
     * for mismatched first rows.
     */
    @Test (expected=ArithmeticException.class)
    public void testValue_WrongFirstRows() {
        System.out.println("value(wrong first rows)");
        Matrix<Double> firstInput = MatrixReal.Value(1, 3, 0, 3, 4.5);
        Matrix<Double> secondInput = MatrixReal.Identity(0, 3);
        HadamardReal instance = this._instance;
        instance.value(firstInput, secondInput);
    }

    /**
     * Test of value method, of class HadamardReal,
     * for mismatched rows.
     */
    @Test (expected=ArithmeticException.class)
    public void testValue_WrongRows() {
        System.out.println("value(wrong rows)");
        Matrix<Double> firstInput = MatrixReal.Value(0, 4, 0, 3, 4.5);
        Matrix<Double> secondInput = MatrixReal.Identity(0, 3);
        HadamardReal instance = this._instance;
        instance.value(firstInput, secondInput);
    }
}