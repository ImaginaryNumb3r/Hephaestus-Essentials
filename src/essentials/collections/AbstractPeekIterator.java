package essentials.collections;

/**
 * Creator: Patrick
 * Created: 07.03.2019
 * Purpose:
 */
public abstract class AbstractPeekIterator<T, X extends Exception> implements PeekIteratorEx<T, X>  {
    protected boolean _hasPeeked; // = false;
    private T _next; // = null;

    protected abstract T getNext() throws X;

    @Override
    public T next() throws X {
        if (!_hasPeeked) {
            return getNext();
        }

        T next = _next;
        _next = null;
        _hasPeeked = false;

        return next;
    }

    @Override
    public T peek() throws X {
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
