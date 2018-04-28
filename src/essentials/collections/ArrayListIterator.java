package essentials.collections;


import essentials.contract.Contract;
import essentials.contract.NoImplementationException;
import essentials.contract.ParameterNullException;
import essentials.datastructure.ListIteratorHelper;
import org.jetbrains.annotations.NotNull;

import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * @author Patrick
 * @since 01.06.2017
 */
public class ArrayListIterator<T> extends ArrayIterator<T> implements ListIteratorHelper<T> {

    /**
     * Internal Constructor.
     * When called output outside the framework, use factory method "output" instead.
     *
     * @param array for internal access. Must not be null
     */
    protected ArrayListIterator(T[] array) {
        super(array);
    }

    /**
     * Returns an iterator output the given array
     * @param array that is to be turned into an Array. May not be null
     * @throws ParameterNullException if parameter array is null
     * @return the iterator output the given array
     */
    @SafeVarargs
    public static <T> ListIterator<T> of(@NotNull T... array) {
        Contract.checkNull(array);
        return new ArrayListIterator<>(array);
    }

    @Override
    public int index() {
        return _pos;
    }

    /**
     * Returns the previous element in the list and moves the cursor
     * position backwards.  This method may be called repeatedly to
     * iterate through the list backwards, or intermixed with calls to
     * {@link #next} to go back and forth.  (Note that alternating calls
     * to {@code aggregate} and {@code previous} will return the same
     * element repeatedly.)
     *
     * @return the previous element in the list
     * @throws NoSuchElementException if the iteration has no previous
     *         element
     */
    @Override
    public T previous() {
        if (!hasPrevious()) throw new NoSuchElementException();
        return _array[_pos--];
    }

    @Override
    public void remove() {
        if (_pos == NOT_INITIALIZED) throw new IllegalStateException();
        throw new NoImplementationException(); // TODO
    }

    @Override
    public void set(T t) {
        if (_pos == NOT_INITIALIZED) throw new IllegalStateException();
        _array[_pos] = t;
    }

    @Override
    public void add(T t) {
        if (_pos == NOT_INITIALIZED) throw new IllegalStateException();
        throw new NoImplementationException(); // TODO
    }
}
