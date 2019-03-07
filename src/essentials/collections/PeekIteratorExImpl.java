package essentials.collections;

import essentials.annotations.Package;

/**
 * Creator: Patrick
 * Created: 06.03.2019
 */
@Package class PeekIteratorExImpl<T, X extends Exception> extends AbstractPeekIterator<T, X> {
    private final IteratorEx<T, X> _iterator;

    PeekIteratorExImpl(IteratorEx<T, X> iterator) {
        _iterator = iterator;
    }

    @Override
    public boolean hasNext() {
        return _iterator.hasNext();
    }

    @Override
    protected T getNext() throws X {
        return _iterator.next();
    }
}
