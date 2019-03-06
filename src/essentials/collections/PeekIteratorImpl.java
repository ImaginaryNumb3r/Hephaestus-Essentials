package essentials.collections;

import essentials.annotations.Package;

import java.util.Iterator;

/**
 * Creator: Patrick
 * Created: 06.03.2019
 */
@Package class PeekIteratorImpl<T> implements PeekIterator<T> {
    private final Iterator<T> _iterator;
    private boolean _hasPeeked;
    private T _next;

    @Package PeekIteratorImpl(Iterator<T> iterator) {
        _iterator = iterator;
    }

    @Override
    public boolean hasNext() {
        return _iterator.hasNext();
    }

    @Override
    public T next() {
        if (!_hasPeeked) {
            return _iterator.next();
        }

        T next = _next;
        _next = null;
        _hasPeeked = false;

        return next;
    }

    @Override
    public T peek() {
        if (_hasPeeked) {
            return _next;
        }

        // Only set peeking to true after calling next.
        // If next throws an exception, peek would have undefined behaviour on subsequent calls.
        _next = next();
        _hasPeeked = true;

        return _next;
    }
}
