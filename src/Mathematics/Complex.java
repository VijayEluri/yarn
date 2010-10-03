/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Mathematics;

import Mathematics.Norm.ComplexP;

/**
 * Implementation of a
 * <a href="http://en.wikipedia.org/wiki/Complex_number">complex number</a>.<br>
 * The class Complex is implemented as
 * <a href="http://en.wikipedia.org/wiki/Immutable_object">immutable</a>.
 * @author Rune Dahl Iversen
 */
public final class Complex
        implements Additive<Complex>, Conjugate<Complex>, Divisible<Complex>,
        Invertible<Complex>, Multiplicative<Complex> {
    private final double _real;
    private final double _imaginary;
    private final double _modulus;
    private final double _argument;

    // The Euclidian Norm to calculate the modulus of complex numbers.
    private final static ComplexP __euclidean = new ComplexP(2.0);

    /**
     * Create an instance of a {@see Complex complex} number using
     * <a href="http://en.wikipedia.org/wiki/Cartesian_coordinate_system">
     * cartesian coordinates</a>.
     * @param real      Real part of the complex number.
     * @param imaginary Imaginary part of the complex number.
     */
    public Complex(final double real, final double imaginary) {
        this._real = real;
        this._imaginary = imaginary;
        // Compute the Modulus and Argument, for easier future access.
        this._modulus = __euclidean.Value(this);
        this._argument = this._getArgument();
    }

    /**
     * Create an instance of a {@see Complex complex} number from a
     * <a href="http://en.wikipedia.org/wiki/Real_number">real number</a>.
     * @param real Real part of the complex number.
     */
    public Complex(final double real) {
        this(real, 0.0);
    }

    /**
     * Returns the
     * <a href="http://en.wikipedia.org/wiki/Arg_(mathematics)#Principal_value">principal</a>
     * <a href="http://en.wikipedia.org/wiki/Arg_%28mathematics%29">argument</a>
     * of this {@see Complex complex} value.
     * @return The principal argument of this complex value.
     */
    public double getArgument() {
        return this._argument;
    }

    /**
     * Returns the
     * <a href="http://en.wikipedia.org/wiki/Arg_(mathematics)#Principal_value">principal</a>
     * <a href="http://en.wikipedia.org/wiki/Arg_%28mathematics%29">argument</a>
     * of this {@see Complex complex} value shifted by 2·Pi · the specified rotations.
     * @return The argument of this complex value.
     */
    public double getArgument(final int rotations) {
        return this._argument + 2.0 * Math.PI * rotations;
    }

    /**
     * Gets the
     * <a href="http://en.wikipedia.org/wiki/Imaginary_part">imaginary</a>
     * part of this {@see Complex complex} number.
     * @return The imaginary part.
     */
    public double getImaginary() {
        if (Complex.isNaN(this))
            return Double.NaN;
        if (Complex.isInfinite(this))
            return Double.POSITIVE_INFINITY;
        return this._imaginary;
    }

    /**
     * Gets the
     * <a href="http://en.wikipedia.org/wiki/Absolute_value#Complex_numbers">
     * modulus</a> of this {@see Complex complex} number.
     * @return The modulus.
     */
    public double getModulus() {
        return this._modulus;
    }

    /**
     * Gets the <a href="http://en.wikipedia.org/wiki/Real_part">real part</a>
     * of this {@see Complex complex} number.
     * @return The real part.
     */
    public double getReal() {
        if (Complex.isNaN(this))
            return Double.NaN;
        if (Complex.isInfinite(this))
            return Double.POSITIVE_INFINITY;
        return this._real;
    }

    /**
     * Returns this {@see Complex complex} number modified to have the specified
     * <a href="http://en.wikipedia.org/wiki/Arg_%28mathematics%29">argument</a>.
     * @param argument Argument.
     * @return         {@see Complex} number.
     */
    public Complex setArgument(final double argument) {
        return Complex.Polar(this._modulus, argument);
    }

    /**
     * Returns this {@see Complex complex} number modified to have the specified
     * <a href="http://en.wikipedia.org/wiki/Imaginary_part">imaginary</a> value.
     * @param imaginary Imaginary value.
     * @return          {@see Complex} number.
     */
    public Complex setImaginary(final double imaginary) {
        return new Complex(this._real, imaginary);
    }

    /**
     * Returns this {@see Complex complex} number modified to have the specified
     * <a href="http://en.wikipedia.org/wiki/Absolute_value#Complex_numbers">
     * modulus</a>.
     * @param modulus Modulus.
     * @return        {@see Complex} number.
     */
    public Complex setModulus(final double modulus) {
        return Complex.Polar(modulus, this._argument);
    }

    /**
     * Returns this {@see Complex complex} number modified to have the specified
     * <a href="http://en.wikipedia.org/wiki/Real_part">real</a> value.
     * @param real Real value.
     * @return     {@see Complex} number.
     */
    public Complex setReal(final double real) {
        return new Complex(real, this._imaginary);
    }

    public Complex Add(final Complex value) {
        return new Complex(this._real + value._real,
                this._imaginary + value._imaginary);
    }

    public Complex Conjugate() {
        return new Complex(this._real, -this._imaginary);
    }

    public Complex Divide(final Complex denominator) {
        if (Complex.isOrigin(denominator) &&
                Complex.isOrigin(this))
            throw new ArithmeticException("Attempt to divide the complex origin"
                    + " by the complex origin.");
        return this.Multiply(denominator.Inverse());
    }

    public Complex Inverse() {
        if (Complex.isNaN(this))
            return Complex.NaN;
        if (Complex.isInfinite(this))
            return Complex.Origin;
        if (Complex.isOrigin(this))
            return Complex.Infinity;
        return Complex.Polar(1.0 / this._modulus, -this._argument);
    }

    public Complex Multiply(final Complex factor) {
        if (factor == null)
            return null;
        return new Complex(this._real * factor._real -
                this._imaginary * factor._imaginary,
                this._real * factor._imaginary +
                this._imaginary * factor._real);
    }

    public Complex Subtract(final Complex value) {
        return new Complex(this._real - value._real,
                this._imaginary - value._imaginary);
    }

    /**
     * The constant {@see Complex complex} value
     * <a href="http://en.wikipedia.org/wiki/Infinity#Complex_analysis">infinity</a>.
     */
    public static final Complex Infinity =
            new Complex(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);

    /**
     * The constant {@see Complex complex} value
     * <a href="http://en.wikipedia.org/wiki/Origin_%28mathematics%29">origin</a>.
     */
    public static final Complex Origin = new Complex(0.0, 0.0);

    /**
     * The constant {@see Complex complex} value
     * <a href="http://en.wikipedia.org/wiki/NaN">NaN (Not a Number)</a>.
     */
    public static final Complex NaN = new Complex(Double.NaN, Double.NaN);

    /**
     * Returns a {@see Complex complex} number with the specified
     * <a href="http://en.wikipedia.org/wiki/Polar_coordinate_system">polar
     * coordinates</a>.
     * @param modulus  Modulus. (Length)
     * @param argument Argument.
     * @return Complex number.
     */
    public static Complex Polar(final double modulus, final double argument) {
        return new Complex(modulus * Math.cos(argument),
                modulus * Math.sin(argument));
    }

    /**
     * Returns true if the {@see Complex complex} number is
     * <a href="http://en.wikipedia.org/wiki/Infinity#Complex_analysis">infinite</a>.
     * @param complex Complex number.
     * @return        True if the complex number is infinite.
     */
    public static boolean isInfinite(final Complex complex) {
        return complex != null &&
                !Complex.isNaN(complex) &&
                (Double.isInfinite(complex._real) ||
                Double.isInfinite(complex._imaginary));
    }

    /**
     * Returns true if the {@see Complex complex} number is the complex
     * <a href="http://en.wikipedia.org/wiki/Origin_%28mathematics%29">origin</a>.
     * @param complex Complex number.
     * @return        True if the complex number is the complex origin.
     */
    public static boolean isOrigin(final Complex complex) {
        return complex != null &&
                complex._real == 0.0 &&
                complex._imaginary == 0.0;
    }

    /**
     * Returns true if the {@see Complex complex} number is the complex
     * <a href="http://en.wikipedia.org/wiki/NaN">NaN</a>.
     * @param complex Complex number.
     * @return        True if the complex number is the complex NaN.
     */
    public static boolean isNaN(final Complex complex) {
        return complex != null &&
                (Double.isNaN(complex._real) ||
                Double.isNaN(complex._imaginary));
    }

    /**
     * Returns Euler's number <i>e</i> raised to the power of a
     * {@see Complex complex} value. Also known as the
     * <a href="http://en.wikipedia.org/wiki/Exponential_function">exponential</a>
     * function. Special cases:
     * <ul>
     * <li>If the value is Complex.NaN, the result is Complex.NaN.</li>
     * <li>If the value is Complex.Infinity, the result is Complex.NaN.</li>
     * </ul>
     * @param value Complex value to raise <i>e</i> to.
     * @return      The value of <i>e<sup>value</sup></i>, where <i>e</i> is the
     *              base of the natural logarithms
     */
    public static Complex exp(final Complex value) {
        if (isNaN(value) || isInfinite(value))
            return Complex.NaN;
        double modulus = Math.abs(value.getReal()) < 0.1 ?
            Math.expm1(value.getReal()) + 1.0 :
            Math.exp(value.getReal());
        return Polar(modulus, value.getImaginary());
    }

    /**
     * Returns the
     * <a href="http://en.wikipedia.org/wiki/Natural_logarithm">natural logarithm</a>
     * (base <i>e</i>) of a {@see Complex complex} value. Special cases:
     * <ul>
     * <li>If the value is Complex.NaN, the result is Complex.NaN.</li>
     * <li>If the value is Complex.Infinity, the result is Complex.NaN.</li>
     * <li>If the value is Complex.Origin, the result is Complex.Infinity.</li>
     * </ul>
     * @param value Complex value to get the natural logarithm of.
     * @return      The the natural logarithm of the complex value.
     */
    public static Complex log(final Complex value) {
        if (isNaN(value) || isInfinite(value))
            return Complex.NaN;
        if (isOrigin(value))
            return Complex.Infinity;
        double real = Math.abs(value.getModulus() - 1.0) < 0.1 ?
            Math.log1p(value.getModulus() - 1.0) :
            Math.log(value.getModulus());
        return new Complex(real, value.getArgument());
    }

    private double _getArgument() {
        if (Complex.isNaN(this) ||
                Complex.isInfinite(this) ||
                Complex.isOrigin(this))
            return Double.NaN;
        double argument = Math.atan2(this._imaginary, this._real);
        return argument;
    }
}