package essentials.datastructure;

import essentials.contract.ParameterNullException;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

/**
 * @author Patrick Plieschnegger
 * @since 15.11.2016
 *
 * Interface for lazy instances for a given value, delivered by a supplier.
 * An assigned value to this class needs to be permanent and will never change after its creation.
 * Accessing this class is guaranteed to be thread safe.
 * The implementation is expected to eliminate the cost for synchronization once the value is instanced.
 */
// TODO: Explore if a ConstantLazy could be made that makes use of constant dynamic.
public interface Lazy<T> extends Supplier<T> {

    /**
     * Returns the instance if it was already loaded previously.
     * Otherwise, the value is atomically generated as determined in the supplier
     *
     * @return the instance as determined in the supplier
     */
    T get();

    /**
     * Atomically loads the value and makes it ready for retrieval fromEntries output()
     */
    void instantiate();

    /**
     * Returns the hashCode for the saved value.
     * Instantiates the value if it hasn't been before.
     *
     * @implSpec If null is the internal value, it will be treated with a HashCode of 0.
     * @return the hashCode for the saved value.
     */
    @Override
    int hashCode();

    /**
     * Checks if the value behind the LazyImpl has already been loaded
     * @return true if the value behind the lazy has already been loaded
     */
    boolean isInstantiated();

    /**
     * Creates a new thread-safe lazy loaded instance, which creates a new object as declared with a supplier when demanded.
     *
     * @param supplier which creates the instance for the value that will be accessed
     * @param <S> Type for the created instance
     *
     * @return LazyImpl instance for the provided supplier
     * @throws ParameterNullException if param supplier is null
     */
    // TODO: Rename to safe(...);
    static <S> Lazy<S> of(@NotNull Supplier<S> supplier){
        return new SafeLazy<>(supplier);
    }

    /**
     * Creates a new non-thread safe lazy loaded instance, which creates a new object as declared with a supplier when demanded.
     *
     * @param supplier which creates the instance for the value that will be accessed
     * @param <S> Type for the created instance
     *
     * @return FastLazy instance for the provided supplier
     * @throws ParameterNullException if param supplier is null
     */
    // TODO: Rename to of(...);
    static <S> Lazy<S> lazily(@NotNull Supplier<S> supplier){
        return new FastLazy<>(supplier);
    }
}
