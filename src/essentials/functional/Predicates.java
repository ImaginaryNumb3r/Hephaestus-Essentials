package essentials.functional;

import java.util.Random;
import java.util.function.Predicate;

/**
 * @author Patrick
 * @since 10.01.2018
 */
public final class Predicates {

    public <T, U> boolean alwaysTrue(T t, U u) {
        return true;
    }

    public <T> boolean alwaysTrue(T t) {
        return true;
    }

    public <T, U> boolean alwaysFalse(T t, U u) {
        return false;
    }

    public <T> boolean alwaysFalse(T t) {
        return false;
    }

    public <T, U> boolean random(T t, U u) {
        return new Random().nextBoolean();
    }

    public <T> boolean random(T t) {
        return new Random().nextBoolean();
    }
}
