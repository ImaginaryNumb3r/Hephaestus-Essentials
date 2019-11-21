package essentials.util;

import essentials.contract.InstanceNotAllowedException;

import java.util.OptionalInt;
import java.util.function.IntPredicate;

/**
 * @author Patrick Plieschnegge
 * @since 13.05.2019
 */
public final class Numbers {
    public static double ONE_THIRD = 1d / 3;
    public static double TWO_THIRD = 2d / 3;

    private Numbers() {
        throw new InstanceNotAllowedException(getClass());
    }

    /**
     * @param value that is to be rounded.
     * @param roundSteps the granularity of the rounding.
     * @return the rounded value
     */
    public static int round(int value, int roundSteps) {
        int residual = value % roundSteps;
        boolean roundUp = residual > roundSteps / 2;

        return roundUp
                ? value + roundSteps - residual
                : value - residual;
    }

    /**
     * @param value that is to be rounded.
     * @param roundSteps the granularity of the rounding.
     * @return the rounded value.
     */
    public static int roundUp(int value, int roundSteps) {
        int residual = value % roundSteps;
        return residual == 0
                ? value
                : value + roundSteps - residual;
    }

    public static OptionalInt parseInt(CharSequence string) {
        int lastIndex = string.length() - 1;
        IntPredicate isNumber = ch -> ch >= '0' && ch <= '9';
        char ch = string.charAt(lastIndex);

        // Ensure that last digit is a number. Character '-' is not valid.
        if (!isNumber.test(ch)) {
            return OptionalInt.empty();
        }

        int value = string.charAt(lastIndex) - '0';
        int mult = 10;

        for (int i = lastIndex - 1; i >= 0; --i) {
            ch = string.charAt(i);

            if (isNumber.test(ch)) {
                value += (ch - '0') * mult;
                mult *= 10;
            } else if (ch != '_') {
                return OptionalInt.empty();
            }
        }

        return OptionalInt.of(value);
    }

    public static OptionalInt box(Integer value) {
        return value == null ? OptionalInt.empty() : OptionalInt.of(value);
    }
}
