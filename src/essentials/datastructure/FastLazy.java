package essentials.datastructure;

import essentials.annotations.Package;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

import static essentials.util.HashGenerator.permutate;

/**
 * @author Patrick Plieschnegger
 * @since 16.05.2018
 * A non thread-safe implementation of Lazy.
 */
@Package class FastLazy<T> implements Lazy<T> {
    private final Supplier<T> _supplier;
    private boolean _isInstantiated;
    private T _value;

    /**
     * Creates a new instance, which creates a new object as declared with a supplier when demanded.
     * @param supplier which creates the instance to the value that will be accessed
     */
    @Package FastLazy(@NotNull Supplier<T> supplier){
        _supplier = supplier;
    }

    /**
     * Atomically returns the instance as determined in the supplier.
     * @return the already as determined in the supplier
     */
    @Override
    public T get() {
        instantiate();
        return _value;
    }

    /**
     * Loads the value internally
     */
    @Override
    public void instantiate(){
        if (!_isInstantiated) {
            _isInstantiated = true;
            _value = _supplier.get();
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
     *
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
     *
     * @return the hashCode for the saved value.
     */
    @Override
    public int hashCode() {
        instantiate();
        return permutate(_value);
    }
}
