package essentials.functional.exception;


import java.util.function.Function;

/**
 * @since Patrick Plieschnegger
 * Created: 20.11.2017
 * Purpose:
 */

public interface FunctionEx<T, R, X extends Exception> extends Function<T, R> {

    R tryApply(T t) throws X;

    /**
     * It is discouraged to call this method directly and only exists to perform an implementation of accept.
     * The method is deprecated to communicate this very clearly.
     *
     * @param t the function argument
     * @throws FunctionalMappingException if an exception of type X occurs.
     *         Will contain exception of type X as inner exception
     * @return the function result
     */
    @Deprecated
    @Override
    default R apply(T t){
        try {
            return tryApply(t);
        } catch (Exception ex) {
            throw new FunctionalMappingException(ex);
        }
    }
}
