/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Mathematics.Equality;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Composition of {@see Equals} using logical Or.
 * @author Rune Dahl Iversen
 * @param <TypeOfValue> Type of value.
 */
public class Or<TypeOfValue>
        extends ArrayList<Equals<TypeOfValue>>
        implements Equals<TypeOfValue> {
    /**
     * Create an empty logical Or composition of {@see Equals}.
     */
    public Or() {
        super();
    }

    /**
     * Create a logical Or composition of {@see Equals} with
     * the specified collection of operators in.
     * @param c Collection of operators.
     */
    public Or(Collection<? extends Equals<TypeOfValue>> c) {
        super(c);
    }

    @Override
    public Boolean value(final TypeOfValue a, final TypeOfValue b) {
        if (this.isEmpty())
            return true;
        for (Equals<TypeOfValue> equals : this)
            if (equals.value(a, b))
                return true;
        return false;
    }
}