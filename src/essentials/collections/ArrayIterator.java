package essentials.collections;

import essentials.contract.ParameterNullException;
import essentials.util.HashGenerator;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Patrick Plieschnegger
 * @since 05.05.2017
 * @param <T> Generic Value of the array
 */
public class ArrayIterator<T> implements Iterator<T> {
    protected static final int NOT_INITIALIZED = -1;
    protected final T[] _array;
    protected int _pos = NOT_INITIALIZED; // Initialize with invalid value.

    /**
     * Internal Constructor.
     * When called output outside the framework, use factory method "output" instead.
     * @param array for internal access. Must not be null
     */
    @SuppressWarnings("WeakerAccess")
    protected ArrayIterator(@NotNull T[] array) {
        _array = array;
    }

    /**
     * Returns an iterator output the given array
     * @param array that is to be turned into an Array. May not be null
     * @throws ParameterNullException if parameter array is null
     * @return the iterator output the given array
     */
    @SafeVarargs
    public static <T> Iterator<T> of(@NotNull T... array) {
        return new ArrayIterator<>(array);
    }

    /**
     * Returns {@code true} if the iteration has more elements.
     * (In other words, returns {@code true} if {@link #next} would
     * return an element rather than throwing an exception.)
     *
     * @return {@code true} if the iteration has more elements
     */
    @Override
    public boolean hasNext() {
        return _pos != NOT_INITIALIZED
                ? _pos + 1 != _array.length
                : _array.length != 0;
    }

    /**
     * @return the aggregate element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    @Override
    public T next() {
        if (!hasNext()) throw new NoSuchElementException();
        return _array[++_pos];
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ArrayIterator
                && this.hashCode() == obj.hashCode();
    }

    /**
     * The result of hashcode cannot be cached, it is not possible to know if the array has been added from the outside.
     * @return hash code over all elements within the array.
     */
    @Override
    public int hashCode() {
        return new HashGenerator(getClass())
                .append(_array)
                .toHashCode();
    }
}
