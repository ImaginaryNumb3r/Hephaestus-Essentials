package essentials.contract;

import java.util.function.Supplier;

/**
 * @author Patrick Plieschnegger
 */
public final class Nulls {

    private Nulls() {
        throw new InstanceNotAllowedException(getClass());
    }

    public static <T> T init(T value, Supplier<T> initializer) {
        return value != null ? value : initializer.get();
    }

    public static <T> T ifNull(T value, T supplement) {
        return value != null ? value : supplement;
    }
}
