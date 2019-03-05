package essentials.datastructure;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.function.Supplier;

import static essentials.util.HashGenerator.permutate;

/**
 * @author Patrick
 * @since 15.11.2016
 * @implNote Uses the boolean {@code _isInstantiated} for loading checks instead for a null check on the value. <br>
 *     Since the actual value might be null, no null checks can be performed to check if the lazy has been loaded already
 *
 * Creates a lazy instance to a value, delivered by a supplier.
 * An assigned value to this class is permanent and will never change.
 * Accessing this class is thread safe. However, the overhead for synchronization is gone once the value is instantiated.
 */
@SuppressWarnings("WeakerAccess")
class SafeLazy<T> implements Lazy<T>, Serializable {
    private final Supplier<T> _supplier;
    private boolean _isInstantiated;
    private T _value;

    /**
     * Creates a new instance, which creates a new object as declared with a supplier when demanded.
     * @param supplier which creates the instance for the value that will be accessed
     */
    protected SafeLazy(@NotNull Supplier<T> supplier){
        _supplier = supplier;
    }

    /**
     * Atomically returns the already instance as determined in the supplier.
     * @return the already instance as determined in the supplier
     */
    @Override
    public T get() {
        instantiate();
        return _value;
    }

    /**
     * Atomically loads the value internally
     */
    @Override
    public void instantiate(){
        if (!_isInstantiated){
            // Only enter synchronized block if the value has not been loaded already
            synchronized (this) {
                if (!_isInstantiated) {
                    _isInstantiated = true;
                    _value = _supplier.get();
                }
            }
        }
    }

    /**
     * Checks if the value behind the LazyImpl has already been loaded
     * @return true if the value behind the lazy has already been loaded
     */
    @Override
    public boolean isInstantiated(){
        return _isInstantiated;
    }

    /**
     * Returns true if the given object is also a Lazy type with an equal internal value
     * Instantiates the value if it hasn't been before.
     * @param obj the other object
     * @return true if the given object is also a Lazy type with an equal internal value
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof SafeLazy
                && get().equals(((SafeLazy) obj).get());
    }

    /**
     * Returns the hashCode for the saved value.
     * Instantiates the value if it hasn't been before.
     * Returns 0 as hashcode if the saved value is null
     * @return the hashCode for the saved value.
     */
    @Override
    public int hashCode() {
        instantiate();
        return permutate(_value);
    }
}