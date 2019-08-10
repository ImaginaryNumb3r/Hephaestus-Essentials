package essentials.functional.exception;

import java.util.function.BinaryOperator;

/**
 * @since Patrick Plieschnegger
 * Created: 16.05.2018
 * Purpose:
 */
@FunctionalInterface
public interface BinaryOperatorEx<T, X extends Exception> extends BinaryOperator<T> {

    T tryApply(T t1, T t2) throws X;

    /**
     * It is discouraged to call this method directly and only exists to perform an implementation of accept.
     * The method is deprecated to communicate this very clearly.
     *
     * @param t1 the first function argument
     * @param t2 the second function argument
     * @throws FunctionalMappingException if an exception of type X occurs.
     *         Will contain exception of type X as inner exception
     * @return the function result
     */
    @Deprecated
    @Override
    default T apply(T t1, T t2){
        try {
            return tryApply(t1, t2);
        } catch (Exception ex) {
            throw new FunctionalMappingException(ex);
        }
    }
}
