package essentials.util;

import essentials.contract.InstanceNotAllowedException;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author Patrick Plieschnegger
 */
public final class Nulls {

    private Nulls() {
        throw new InstanceNotAllowedException(getClass());
    }

    public static <T> T init(T value, T fallback) {
        return value != null ? value : fallback;
    }

    public static <T> T init(T value, Supplier<T> initializer) {
        return value != null ? value : initializer.get();
    }

    public static <T> T ifNull(T value, T supplement) {
        return value != null ? value : supplement;
    }

    public static <T> void ifnull(T object, Runnable action) {
        if (object == null) {
            action.run();
        }
    }

    public static <T> void ifPresent(T object, Consumer<T> consumer) {
        if (object != null) {
            consumer.accept(object);
        }
    }

    public static <T> void ifPresent(T object, Runnable action) {
        if (object != null) {
            action.run();
        }
    }

    public static <T> Optional<T> box(boolean condition, T instance) {
        return condition ? Optional.of(instance) : Optional.empty();
    }
}
