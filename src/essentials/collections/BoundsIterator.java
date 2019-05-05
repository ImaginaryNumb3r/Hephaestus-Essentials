package essentials.collections;

import org.jetbrains.annotations.NotNull;

import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Creator: Patrick
 * Created: 05.04.2019
 */
public class BoundsIterator implements ListIterator<Integer> {
    private final int _start;
    private final int _end;
    private final boolean _forward;
    private int _pos;

    public BoundsIterator(int start, int end) {
        _forward = start < end;
        _start = start;
        _end = end;
        _pos = start;
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

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Cannot mutate a bound of integers.");
    }

    @Override
    public void set(Integer integer) {
        throw new UnsupportedOperationException("Cannot mutate a bound of integers.");
    }

    @Override
    public void add(Integer integer) {
        throw new UnsupportedOperationException("Cannot mutate a bound of integers.");
    }
}
