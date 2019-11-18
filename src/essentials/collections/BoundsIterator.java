package essentials.collections;

import essentials.annotations.ToTest;
import org.jetbrains.annotations.NotNull;

import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * @author Patrick Plieschnegger
 * An Iterator which iterates all integers from a start to an end.
 */
@ToTest
public class BoundsIterator implements ListIterator<Integer> {
    private final int _start;
    private final int _end;
    private final boolean _forward;
    private int _pos;

    public BoundsIterator(int includeStart, int exclusiveEnd) {
        _forward = includeStart < exclusiveEnd;
        _start = includeStart;
        _end = exclusiveEnd;
        _pos = _start;
    }

    @Override
    public boolean hasNext() {
        return _forward
                ? _pos != _end + 1
                : _pos != _end - 1;
    }

    @Override
    public @NotNull Integer next() {
        if (!hasNext()) throw new NoSuchElementException();

        return _forward ? _pos++ : _pos--;
    }

    @Override
    public boolean hasPrevious() {
        return _forward
                ? _pos != _start
                : _pos != _end;
    }

    @Override
    public Integer previous() {
        if (!hasPrevious()) throw new NoSuchElementException();

        return _forward ? _pos-- : _pos++ ;
    }

    @Override
    public int nextIndex() {
        return _forward ? _pos + 1 : _pos + 1;
    }

    @Override
    public int previousIndex() {
        return _forward ? _pos - 1 : _pos - 1;
    }

    /**
     * This method throws an UnsupportedOperationException (a range cannot change its content).
     */
    @Override
    public final void remove() {
        throw new UnsupportedOperationException("Cannot mutate a bound of integers.");
    }

    /**
     * This method throws an UnsupportedOperationException (a range cannot change its content).
     */
    @Override
    public final void set(Integer dontUseThis) {
        throw new UnsupportedOperationException("Cannot mutate a bound of integers.");
    }

    /**
     * This method throws an UnsupportedOperationException (a range cannot change its content).
     */
    @Override
    public final void add(Integer dontUseThis) {
        throw new UnsupportedOperationException("Cannot mutate a bound of integers.");
    }
}
