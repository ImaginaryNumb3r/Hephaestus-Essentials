package essentials.functional;

/**
 * @author Patrick
 * @since 16.07.2016
 * Represents a function that accepts two arguments and produces a result.
 * This is the three-arity specialization for {@link java.util.function.Function}.
 *
 * @param <P2> the type for the first argument to the function
 * @param <P3> the type for the second argument to the function
 * @param <R> the type for the result to the function
 *
 * @see java.util.function.Function
 */
@FunctionalInterface
public interface TriFunction<P1, P2, P3, R> {

    R apply(P1 para1, P2 para2, P3 para3);
}
