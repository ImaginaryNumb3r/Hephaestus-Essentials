package essentials.functional.exception;

import java.util.function.Predicate;

/**
 * @since Patrick Plieschnegger
 * Created: 16.05.2018
 * Purpose:
 */
@FunctionalInterface
public interface PredicateEx<T, X extends Exception> extends Predicate<T> {

    default boolean test(T item) {
        boolean result;
        try {
            result = tryTest(item);
        } catch (Exception ex) {
            throw new FunctionalMappingException(ex);
        }

        return result;
    }

    boolean tryTest(T item) throws X;

}
