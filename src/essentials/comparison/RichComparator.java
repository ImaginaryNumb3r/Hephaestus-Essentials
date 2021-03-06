package essentials.comparison;

import java.util.Comparator;

/**
 * @author Patrick Plieschnegger
 * Purpose: A more readable Comparator which is compatible to the java.lang.Comparator.
 * The provided methods should be self-explanatory.
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

    /**
     * Creates a RichComparator instance from a Comparator.
     * @param comparator the original comparator
     * @return the Rich Comparator.
     */
    static <T> RichComparator<T> of(Comparator<T> comparator) {
        return comparator::compare;
    }

    static boolean isSmaller(int i) {
        return i < 0;
    }

    static boolean isSmallerOrEqual(int i) {
        return i <= 0;
    }

    static boolean isEqual(int i) {
        return i == 0;
    }

    static boolean isGreaterOrEqual(int i) {
        return i >= 0;
    }

    static boolean isGreater(int i) {
        return i > 0;
    }
}
