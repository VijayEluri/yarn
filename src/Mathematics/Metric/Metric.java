/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Mathematics.Metric;

import Mathematics.Function.Operator;

/**
 * Interface for a
 * <a href="http://en.wikipedia.org/wiki/Metric_%28mathematics%29">metric</a>.
 * @author Rune Dahl Iversen
 * @param <TypeOfValue> Type of value.
 */
public interface Metric<TypeOfValue>
        extends Operator<TypeOfValue, TypeOfValue, Double> {
    /**
     * Returns the distance under this metric between the 2 specified values.
     * @param firstInput  First value.
     * @param secondInput Second value.
     * @return            The distance according to this metric.
     */
    @Override
    public Double value(final TypeOfValue firstInput, final TypeOfValue secondInput);
}