package essentials.functional.exception;

import java.util.function.Supplier;

/**
 * @since Patrick Plieschnegger
 * @since 29.11.2016
 */
// TODO: Consider removal or make experimental
@FunctionalInterface
public interface SupplierEx<T, X extends Exception> extends Supplier<T> {

    /**
     * It is discouraged to call this method directly and only exists to perform an implementation of accept.
     * The method is deprecated to communicate this very clearly.
     */
    @Deprecated
    default T get() throws RuntimeException {
        T value;

        try {
            value = tryGet();
        } catch (Exception ex) {
            throw new FunctionalMappingException(ex);
        }

        return value;
    }

    T tryGet() throws X;

}
