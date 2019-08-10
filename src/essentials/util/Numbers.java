package essentials.util;

import essentials.contract.InstanceNotAllowedException;

/**
 * @author Patrick
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
}
