package essentials.functional.exception;

import java.util.function.Consumer;

/**
 * Creator: Patrick
 * Created: 16.05.2018
 * Purpose:
 */
@FunctionalInterface
public interface ConsumerEx<T, X extends Exception> extends Consumer<T> {

    /**
     * It is discouraged to call this method directly and only exists to perform an implementation of accept.
     * The method is deprecated to communicate this very clearly.
     *
     * In the future, FunctionalMappingException will be removed.
     */
    @Deprecated
    @Override
    default void accept(T item) {
        try {
            tryAccept(item);
        } catch (Exception ex) {
            throw new FunctionalMappingException(ex);
        }
    }

    /**
     * This is the preferred method for invoking an action.
     */
    T tryAccept(T item) throws X;

}
