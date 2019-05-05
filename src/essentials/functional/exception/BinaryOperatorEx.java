package essentials.functional.exception;

import java.util.function.BinaryOperator;

/**
 * Creator: Patrick
 * Created: 16.05.2018
 * Purpose:
 */
@FunctionalInterface
public interface BinaryOperatorEx<T, X extends Exception> extends BinaryOperator<T> {

    T tryApply(T t1, T t2) throws X;

    /**
     * Applies this function to the given argument.
     *
     * @param t1 the first function argument
     * @param t2 the second function argument
     * @throws FunctionalMappingException if an exception of type X occurs.
     *         Will contain exception of type X as inner exception
     * @return the function result
     */
    @Override
    default T apply(T t1, T t2){
        try {
            return tryApply(t1, t2);
        } catch (Exception ex) {
            throw new FunctionalMappingException(ex);
        }
    }
}
