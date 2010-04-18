/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Mathematics.Function;

import Mathematics.Vector.VectorReal;
import Validation.*;

/**
 * Implementation of a {@see Polynomial polynomial} with real coefficients,
 * input and scalar.
 * @author Rune Dahl Iversen
 */
public final class PolynomialReal implements Polynomial<Double, Double, Double> {
    private Validator<Double> _validator;
    private double[] _coefficients;

    /**
     * Creates a polynomial of the specified degree with all coefficients
     * set to 0.
     * @param degree Degree.
     */
    public PolynomialReal(final int degree) {
        this(new VectorReal(degree + 1));
    }

    /**
     * Creates a polynomial with the coefficients and degree determined by
     * the array of coefficients.
     * <br>- The degree will be the length of the array minus one.
     * <br>- The coefficient with index i will be the coefficient of the
     * polynomial degree i.
     * @param coefficients Array of coefficients.
     */
    public PolynomialReal(final Double[] coefficients) {
        this._validator = this._setValidator();
        this._setCoefficients(coefficients);
    }

    /**
     * Creates a polynomial with the coefficients and degree determined by
     * the array of coefficients.
     * <br>- The degree will be the length of the array minus one.
     * <br>- The coefficient with index i will be the coefficient of the
     * polynomial degree i.
     * @param coefficients Array of coefficients.
     */
    public PolynomialReal(final double[] coefficients) {
        this._validator = this._setValidator();
        Double[] c = new Double[coefficients.length];
        for (int d = 0; d < c.length; d++)
            c[d] = coefficients[d];
        this._setCoefficients(c);
    }

    /**
     * Creates a polynomial with the coefficients and degree determined by
     * the vector of coefficients.
     * <br>- The degree will be the dimension of the vector minus one.
     * <br>- The value of dimension i will be the coefficient of the
     * polynomial degree i.
     * @param coefficients Vector of coefficients.
     */
    public PolynomialReal(final VectorReal coefficients) {
        this(coefficients.ToArray());
    }

    public Double getCoefficient(final int degree) {
        return this._coefficients[degree];
    }

    public int getDegree() {
        return this._coefficients.length - 1;
    }

    public Polynomial<Double, Double, Double> setCoefficient(final int degree,
            final Double value) {
        if (degree < 0)
            throw new IllegalArgumentException("The specified degree must be " +
                    "non-negative.");
        if (this.getDegree() < degree)
            throw new IllegalArgumentException("The specified degree must be " +
                    "less than or equal to the degrees of this polynomial.");
        if (!this._validator.isValid(value))
            throw new IllegalArgumentException(
                    this._validator.Message(value, "Value"));
        Double[] coefficients = new Double[this._coefficients.length];
        for (int d = 0; d < this._coefficients.length; d++)
            if (d != degree)
                coefficients[d] = this._coefficients[d];
            else
                coefficients[d] = value;
        return new PolynomialReal(coefficients);
    }

    public Function<Double, Double> getDifferential() {
        Double[] coefficients = new Double[Math.max(this.getDegree() - 1, 1)];
        coefficients[0] = 0.0;
        for (int degree = 1; degree < this.getDegree(); degree++)
            coefficients[degree - 1] = ((double)degree) * this._coefficients[degree];
        return new PolynomialReal(coefficients);
    }

    public Double getDifferential(final Double input) {
        double output = 0.0;
        double value = 1.0;
        for (int degree = 1; degree < this._coefficients.length; degree++)
        {
            output += ((double)degree) * this._coefficients[degree] * value;
            value *= input;
        }
        return output;
    }

    public Double Value(final Double input) {
        double output = 0.0;
        double value = 1.0;
        for (int degree = 0; degree < this._coefficients.length; degree++)
        {
            output += this._coefficients[degree] * value;
            value *= input;
        }
        return output;
    }

    public Polynomial<Double, Double, Double> Add(
            final Polynomial<Double, Double, Double> value) {
        int degree = Math.max(this.getDegree(), value.getDegree());
        Double[] sums = new Double[degree + 1];
        for (int d = 0; d <= degree; d++)
        {
            sums[d] = 0.0;
            if (d <= this.getDegree())
                sums[d] += this.getCoefficient(d);
            if (d <= value.getDegree())
                sums[d] += value.getCoefficient(d);
        }
        return new PolynomialReal(sums);
    }

    public Polynomial<Double, Double, Double> Subtract(
            final Polynomial<Double, Double, Double> value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Polynomial<Double, Double, Double> Multiply(
            final Polynomial<Double, Double, Double> factor) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Polynomial<Double, Double, Double> Scale(final Double scalar) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void _setCoefficients(final Double[] values) {
        this._coefficients = new double[values.length];
        for (int dim = 0; dim < values.length; dim++)
        {
            if (this._validator.isValid(values[dim]))
                this._coefficients[dim] = values[dim];
            else
                throw new IllegalArgumentException(
                        this._validator.Message(values[dim],
                        "The value of dimension " + Integer.toString(dim)));
        }
    }

    private Validator<Double> _setValidator() {
        And<Double> validator = new And<Double>();
        validator.add(new NotNull<Double>());
        validator.add(new DoubleIsNumeric());
        validator.add(new DoubleIsFinite());
        return validator;
    }
}