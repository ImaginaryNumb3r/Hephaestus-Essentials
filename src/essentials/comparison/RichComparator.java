package essentials.comparison;

import java.util.Comparator;

/**
 * @author Patrick Plieschnegger
 * Purpose: A more readable Comparator which is compatible to the original java.lang interface.
 */
public interface RichComparator<T> extends Comparator<T> {

    default boolean smaller(T val1, T val2) {
        return compare(val1, val2) < 0;
    }

    default boolean smallerOrEqual(T val1, T val2) {
        return compare(val1, val2) <= 0;
    }

    default boolean equals(T val1, T val2) {
        return compare(val1, val2) == 0;
    }

    default boolean greaterOrEqual(T val1, T val2) {
        return compare(val1, val2) >= 0;
    }

    default boolean greater(T val1, T val2) {
        return compare(val1, val2) > 0;
    }
}
