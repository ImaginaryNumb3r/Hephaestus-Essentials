package essentials.functional;

import java.util.function.BiFunction;

/**
 * @since Patrick Plieschnegger
 * Created: 28.02.2019
 */
@FunctionalInterface
public interface Accumulator<Acc, Buffer> extends BiFunction<Acc, Buffer, Buffer> {

    Buffer apply(Acc t, Buffer u) throws RuntimeException;

    default Buffer noOp(Acc acc, Buffer buffer) {
        return buffer;
    }
}
