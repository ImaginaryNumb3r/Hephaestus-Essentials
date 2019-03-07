package essentials.collections;

import essentials.annotations.Package;

import java.util.Iterator;

/**
 * Creator: Patrick
 * Created: 06.03.2019
 */
@Package class PeekIteratorImpl<T> extends AbstractPeekIterator<T, RuntimeException> implements PeekIterator<T> {
    private final Iterator<T> _iterator;

    PeekIteratorImpl(Iterator<T> iterator) {
        _iterator = iterator;
    }


    @Override
    protected T getNext() throws RuntimeException {
        return _iterator.next();
    }

    @Override
    public boolean hasNext() {
        return _iterator.hasNext();
    }

    @Override
    public T peek() {
        return super.peek();
    }
}
