package essentials.functional.exception;

import java.util.function.Consumer;

/**
 * Creator: Patrick
 * Created: 16.05.2018
 * Purpose:
 */
@FunctionalInterface
public interface ConsumerEx<T, X extends Exception> extends Consumer<T> {

    @Override
    default void accept(T item) {
        try {
            tryAccept(item);
        } catch (Exception ex) {
            throw new FunctionalMappingException(ex);
        }
    }

    T tryAccept(T item) throws X;

}
