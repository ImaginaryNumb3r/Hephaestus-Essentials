package essentials.functional.exception;


/**
 * @since Patrick Plieschnegger
 * @since 23.01.2017
 */
// TODO: Consider removal or make experimental
@FunctionalInterface
public interface RunnableEx<X extends Throwable> extends Runnable{

    /**
     * It is discouraged to call this method directly and only exists to perform an implementation of accept.
     * The method is deprecated to communicate this very clearly.
     */
    @Deprecated
    @Override
    default void run(){
        try {
            tryRun();
        } catch (Throwable throwable) {
            throw new FunctionalMappingException(throwable);
        }
    }

    /**
     * Executes a method, but an exception may also be thrown
     * @throws X of specified type
     */
    void tryRun() throws X;

}
