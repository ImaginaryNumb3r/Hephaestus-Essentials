package essentials.functional;

import java.util.Random;

/**
 * @since Patrick Plieschnegger
 * @since 10.01.2018
 *
 * Common predicates Predicate and BiPredicate
 */
public final class Predicates {

    public static <T, U> boolean alwaysTrue(T t, U u) {
        return true;
    }

    public static <T> boolean alwaysTrue(T t) {
        return true;
    }

    public static <T, U> boolean alwaysFalse(T t, U u) {
        return false;
    }

    public static <T> boolean alwaysFalse(T t) {
        return false;
    }

    public static <T, U> boolean random(T t, U u) {
        return new Random().nextBoolean();
    }

    public static <T> boolean random(T t) {
        return new Random().nextBoolean();
    }
}
