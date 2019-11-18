package essentials.contract;

/**
 * @author Patrick
 * @since 19.05.2017
 *
 * An exception that is thrown to communicate that a method has no valid implementation.
 * This is a alternative to the disfavoured NotImplementedException and vague UnsupportedOperationException.
 */
public class NoImplementationException extends RuntimeException {

    public NoImplementationException() {
    }

    public NoImplementationException(String message) {
        super(message);
    }
}
