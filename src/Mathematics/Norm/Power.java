/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Mathematics.Norm;

import Validation.*;

/**
 * Abstract class allowing for easier implementation of the
 * <a href="http://en.wikipedia.org/wiki/Norm_%28mathematics%29#p-norm">P-norm</a>.
 * @author Rune Dahl Iversen
 * @param <TypeOfValue> Type of value.
 */
public abstract class Power<TypeOfValue> implements Norm<TypeOfValue> {
    private double _power;
    private And<Double> _powerValidator;

    /**
     * Creates a new instance of a P-norm.
     * @param power Power. Must be greater than or equal to 1 and finite.
     * @throws IllegalArgumentException Power is not finite.
     * @throws IllegalArgumentException Power must be greater than or equal to 1.
     */
    protected Power(double power) {
        this._powerValidator = new And();
        this._powerValidator.add(new DoubleIsFinite());
        this._powerValidator.add(new DoubleGreaterThanOrEqual(1.0));
        this.setPower(power);
    }

    /**
     * Gets the power of this norm.
     * @return The power.
     */
    public double getPower() {
        return this._power;
    }

    /**
     * Sets the power of this P-norm.
     * @param power Power. Must be greater than or equal to 1 and finite.
     * @throws IllegalArgumentException Power is not finite.
     * @throws IllegalArgumentException Power must be greater than or equal to 1.
     */
    public void setPower(double power) {
        if (!this._powerValidator.isValid(power))
            throw new IllegalArgumentException(
                    this._powerValidator.Message(power, "Power"));
        this._power = power;
    }
}