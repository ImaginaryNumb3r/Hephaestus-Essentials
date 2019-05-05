package essentials.util;

import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * Creator: Patrick
 * Created: 21.03.2019
 * Purpose:
 */
public final class Nulls {

    public static <T> void ifPresent(T object, @NotNull Consumer<T> consumer) {
        if (object != null) {
            consumer.accept(object);
        }
    }

    public static <T> void ifPresent(T object, @NotNull Runnable action) {
        if (object != null) {
            action.run();
        }
    }

    public static <T> void ifnull(T object, @NotNull Runnable action) {
        if (object == null) {
            action.run();
        }
    }

    public static <T> T orElse(T object, T other) {
        return object != null ? object : other;
    }

    public static <T> Optional<T> box(boolean condition, T instance) {
        return condition ? Optional.of(instance) : Optional.empty();
    }
}
